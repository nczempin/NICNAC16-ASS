*=$000
	LDA $010	
	STA $200
	LDA $011	
	STA $200
	JMP $000 ; endless loop
*=$010
	.word $aaaa
	.word $5555