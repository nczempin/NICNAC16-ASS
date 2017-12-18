*=$000
	LDA $007
	BAN $004
	LDA $008
	JMP $005
	LDA $009 ;4	
	STA $200
	JMP $000 ; endless loop
.word $6234
.word $BEEF
.word $DEAD ;9
*=$00F
.word $0000