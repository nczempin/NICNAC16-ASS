*=$000
	LDA $00A
	ADD $00B
	STA $200
	JMP $000 ; endless loop
*=$00A
.word $0007
.word $0008
*=$00F
.word $0000