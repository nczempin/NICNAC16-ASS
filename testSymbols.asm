
*=$000
	LDA VALUE1
ENDLESS:
	JMP ENDLESS
*=$05
VALUE1:
.word $0000
VALUE2:
.word $0001
