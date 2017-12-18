*=$000
	LDA $010
	ADD $00B
	STA $100
	ADD $00A
	LDA $100
	STA $200
	JMP $006 ; endless loop
*=$00A
.word $00A9
.word $0008
*=$010
.word $75a5
.word $0001