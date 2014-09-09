*=$000
	LDA $020	; load the value at address 0x20 into the accumulator
	ADD $021	; add the value at address 0x21 to the accumulator
	STA $100	; store the result (value of accumulator) at address $100 (RAM in the very first version of the machine)
	DIO $815
*=$020
	.word $55AA
	.word $1001