*=$000
	BL	$00F ; 0: A <- 0
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
	JMP $017 ; b: NOP
	ADD $0FF ; c: NOP
	ADD $0FF ; d: NOP
	ADD $0FF ; e: NOP
	ADD $0FF ; f: NOP
	BL	$007
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
	ADD $0FF ; 1f: NOP
	JMP $001 ; 20: goto 1
