/**
 * Write your info here
 *
 * @name Nour Khaled ElFaramawy
 * @id 49-13354
 * @labNumber 07
 */

grammar Task7;

/**
 * This rule is to check your grammar using "ANTLR Preview"
 */
test: (ONE | ZERO | ERROR)+ EOF; //Replace the non-fragment lexer rules here

//SEGMENT: ONE | ZERO | ERROR;
ONE: SEGONE;
ZERO: SEGZERO;
ERROR: SEGERROR;

fragment SEGONE: '011' | '111' | '000';
fragment SEGZERO: '001' | '101' | '110' | '010' | '100';
fragment SEGERROR: '0' | '1' | '00' | '01' | '10' | '11';