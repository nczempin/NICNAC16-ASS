#include <iostream>
#include <fstream>
#include <sstream>
#include <map>
#include <unordered_map>
#include <vector>
#include <optional>
#include <set>
#include <iomanip>

class Label {
public:
    explicit Label(std::string n) : name(std::move(n)) {}
    std::optional<int> value;
    std::set<int> references;
    const std::string name;
};

class SymbolTable {
    std::unordered_map<std::string, Label> table;
public:
    std::optional<int> get(const std::string& sym) const {
        auto it = table.find(sym);
        if (it == table.end()) return std::nullopt;
        return it->second.value;
    }
    void put(const std::string& sym, int addr) {
        auto it = table.find(sym);
        if (it == table.end()) {
            Label l(sym);
            l.value = addr;
            table.emplace(sym, std::move(l));
        } else if (!it->second.value) {
            it->second.value = addr;
        } else {
            std::cerr << "Warning: redefining symbol " << sym << " ignored\n";
        }
    }
    std::string handle(const std::string& sym, int addr) {
        auto it = table.find(sym);
        if (it == table.end()) {
            Label l(sym);
            l.references.insert(addr);
            table.emplace(sym, std::move(l));
            return "000";
        }
        if (!it->second.value) {
            it->second.references.insert(addr);
            return "000";
        }
        std::stringstream ss;
        ss << std::hex << std::setfill('0') << std::setw(3) << it->second.value.value();
        return ss.str();
    }
    void print() const {
        for (const auto& [sym, label] : table) {
            if (label.value)
                std::cout << sym << ": " << std::hex << label.value.value() << std::dec << "\n";
            else
                std::cout << sym << ": (undef)\n";
            for (int r : label.references) {
                std::cout << "R:" << r << "\n";
            }
        }
    }
};

static void writeToRom(std::vector<int>& rom, int addr, const std::string& s) {
    int value = std::stoi(s, nullptr, 16);
    if (addr >= 0 && addr < (int)rom.size()) rom[addr] = value;
}

int main(int argc, char** argv) {
    std::string filename = argc > 1 ? argv[1] : "testSymbols.asm";
    std::ifstream in(filename);
    if (!in) {
        std::cerr << "Cannot open " << filename << "\n";
        return 1;
    }
    const int PAGE_SIZE = 0x100;
    std::vector<int> rom(PAGE_SIZE, 0);
    std::vector<int> ram(PAGE_SIZE, 0);

    SymbolTable symbols;
    std::map<std::string,int> opcodes{{"NOP",0b0000},{"JMP",0b0001},{"BL",0b0010},
        {"RET",0b0011},{"LDA",0b0100},{"STA",0b0101},{"ADD",0b0110},{"BAN",0b1000},{"DIO",0b1111}};

    int currentAddress = 0;
    int maxAddress = 0;
    std::string line;
    while (std::getline(in, line)) {
        std::stringstream st(line);
        std::string token;
        while (st >> token) {
            if (token.rfind("*", 0) == 0) {
                processAddressToken(token, currentAddress, maxAddress);
                break;
            } else if (token.rfind(";", 0) == 0) {
                break;
            } else if (token == ".word") {
                processWordDirective(st, rom, currentAddress, maxAddress);
                break;
            } else if (!token.empty() && token.back() == ':') {
                processLabel(token, symbols, currentAddress);
                break;
            } else {
                processOpcode(token, st, opcodes, symbols, rom, currentAddress, maxAddress);
            }
        }
    }
    std::cout << "********" << "\n";
    symbols.print();
    return 0;
}
