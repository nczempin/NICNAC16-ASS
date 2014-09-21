*=$000
	LDA $010
	BAN $003
	LDA $011
	JMP $005
	LDA $012	
	STA $200
	JMP $000 ; endless loop
*=$010
.word $A234
.word $BEEF
.word $DEAD
*=$00F
.word $0000