*=$000
	LDA $0FF ; 0: A <- 0
	STA $10B ; 1: B <- 0
	ADD $0F1 ; 2: A++
	ADD $0FF ; 3: NOP
	ADD $0FF ; 4: NOP
	ADD $0FF ; 5: NOP
	ADD $0FF ; 6: NOP
	ADD $0FF ; 7: NOP
	ADD $0FF ; 8: NOP
	ADD $0FF ; 9: NOP
	ADD $0FF ; a: NOP
	ADD $0FF ; b: NOP
	ADD $0FF ; b: NOP
	ADD $0FF ; c: NOP
	ADD $0FF ; d: NOP
	ADD $0FF ; e: NOP
	ADD $0FF ; f: NOP
	ADD $0FF ; 10: NOP
	ADD $0FF ; 11: NOP
	ADD $0FF ; 12: NOP
	ADD $0FF ; 3: NOP
	ADD $0FF ; 4: NOP
	ADD $0FF ; 5: NOP
	ADD $0FF ; 6: NOP
	ADD $0FF ; 7: NOP
	ADD $0FF ; 8: NOP
	ADD $0FF ; 9: NOP
	ADD $0FF ; a: NOP
	ADD $0FF ; b: NOP
	ADD $0FF ; b: NOP
	ADD $0FF ; c: NOP
	ADD $0FF ; d: NOP
	ADD $0FF ; e: NOP
	ADD $0FF ; f: NOP
*=$020
	BAN $022 ; 20: if A >= $8000 goto 22
	JMP $002 ; 21 goto 2
	LDA $10B ; 22: A <- B
	ADD $0F1 ; 23: A++
	STA $10B ; 24: B <- A
	STA $200 ; 25: LED <- B
	LDA $10B ; 26: A <- B
	BAN $000 ; 27: if B >= $8000 goto 0
	JMP $001 ; 28: goto 1
*=$0F0
.word $0000
.word $0001
*=$0FF
.word $0000
