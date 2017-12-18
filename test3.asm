*=$000
	JMP $003
.word $ffff
.word $ffff
	LDA $001	
	STA $200
	JMP $003 ; endless loop
*=$00F
.word $0000