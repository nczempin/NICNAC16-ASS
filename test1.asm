*=$000
	LDA $004	
	ADD $005	
	STA $100	; store the result (value of accumulator) at address $100 (RAM in the very first version of the machine)
	DIO $815

	.word $55AA
	.word $1001