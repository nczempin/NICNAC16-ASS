*=$000
	LDA $042 ; A <- 0
	BL	$020 ;  subroutine A+=10c5
	ADD $041 ; A+= 2abc
	BL	$030 ; subroutine A += 2abc
	STA $200
	JMP $005
*=$020
	ADD $040
	RET $000 ; TODO: introduce 0-operand operations
	JMP $023
*=$030
	ADD $041
	RET $000
	JMP $033 ; endless
*=$040
.word $10C5
.word $2ABC
.word $0000