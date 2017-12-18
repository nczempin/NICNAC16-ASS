*=$000
	LDA $002	
	BAN $000
	.word $ffff ;negative, so should branch
	.word $66bb