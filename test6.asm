*=$000
	LDA $010
	ADD $011
	BAN $004
	JMP $001
	STA $200
	JMP $005 ; endless loop
*=$00A
.word $00A9
.word $0008
*=$010
.word $7000
.word $0001