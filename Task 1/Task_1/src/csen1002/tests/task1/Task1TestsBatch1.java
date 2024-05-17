package csen1002.tests.task1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task1.RegExToNfa;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task1TestsBatch1 {

	@Test
	public void testRegEx1() {
		RegExToNfa regExToNfa= new RegExToNfa("j;u#jej.e.uu.|.");
		assertEquals("0;1;2;3;5;7;8;9;11;13#j;u#0,j,1;1,e,2;1,e,8;2,e,3;3,j,5;5,e,7;7,e,13;8,u,9;9,u,11;11,e,13#0#13", regExToNfa.toString());
	}

	@Test
	public void testRegEx2() {
		RegExToNfa regExToNfa= new RegExToNfa("k;r;y;z#kzyr.|.");
		assertEquals("0;1;2;3;4;5;7;9#k;r;y;z#0,k,1;1,e,2;1,e,4;2,z,3;3,e,9;4,y,5;5,r,7;7,e,9#0#9", regExToNfa.toString());
	}

	@Test
	public void testRegEx3() {
		RegExToNfa regExToNfa= new RegExToNfa("v;z#e*evz.|*.z|");
		assertEquals("0;1;2;3;4;5;6;7;9;10;11;13;14;15;16;17#v;z#0,e,1;1,e,0;1,e,3;2,e,0;2,e,3;3,e,10;3,e,13;4,e,5;5,e,11;6,v,7;7,z,9;9,e,11;10,e,4;10,e,6;11,e,10;11,e,13;13,e,17;14,z,15;15,e,17;16,e,2;16,e,14#16#17", regExToNfa.toString());
	}

	@Test
	public void testRegEx4() {
		RegExToNfa regExToNfa= new RegExToNfa("d;h;k;m;r#md*kr.h||.");
		assertEquals("0;1;2;3;4;5;6;7;9;10;11;12;13;15#d;h;k;m;r#0,m,1;1,e,4;1,e,12;2,d,3;3,e,2;3,e,5;4,e,2;4,e,5;5,e,15;6,k,7;7,r,9;9,e,13;10,h,11;11,e,13;12,e,6;12,e,10;13,e,15#0#15", regExToNfa.toString());
	}

	@Test
	public void testRegEx5() {
		RegExToNfa regExToNfa= new RegExToNfa("f;y#ey*f*e.*..");
		assertEquals("0;1;2;3;5;6;7;8;9;11;13#f;y#0,e,1;1,e,2;1,e,5;2,y,3;3,e,2;3,e,5;5,e,8;5,e,13;6,f,7;7,e,6;7,e,9;8,e,6;8,e,9;9,e,11;11,e,8;11,e,13#0#13", regExToNfa.toString());
	}

	@Test
	public void testRegEx6() {
		RegExToNfa regExToNfa= new RegExToNfa("g;m;t;v#tmv|v.g||");
		assertEquals("0;1;2;3;4;5;6;7;9;10;11;12;13;14;15#g;m;t;v#0,t,1;1,e,15;2,m,3;3,e,7;4,v,5;5,e,7;6,e,2;6,e,4;7,v,9;9,e,13;10,g,11;11,e,13;12,e,6;12,e,10;13,e,15;14,e,0;14,e,12#14#15", regExToNfa.toString());
	}

	@Test
	public void testRegEx7() {
		RegExToNfa regExToNfa= new RegExToNfa("a;j;m#ajm|e.|");
		assertEquals("0;1;2;3;4;5;6;7;9;10;11#a;j;m#0,a,1;1,e,11;2,j,3;3,e,7;4,m,5;5,e,7;6,e,2;6,e,4;7,e,9;9,e,11;10,e,0;10,e,6#10#11", regExToNfa.toString());
	}

	@Test
	public void testRegEx8() {
		RegExToNfa regExToNfa= new RegExToNfa("j;n#nj*|");
		assertEquals("0;1;2;3;4;5;6;7#j;n#0,n,1;1,e,7;2,j,3;3,e,2;3,e,5;4,e,2;4,e,5;5,e,7;6,e,0;6,e,4#6#7", regExToNfa.toString());
	}

	@Test
	public void testRegEx9() {
		RegExToNfa regExToNfa= new RegExToNfa("d;f;m;p#dfpf.||m*|*");
		assertEquals("0;1;2;3;4;5;7;8;9;10;11;12;13;14;15;16;17;18;19#d;f;m;p#0,d,1;1,e,11;2,f,3;3,e,9;4,p,5;5,f,7;7,e,9;8,e,2;8,e,4;9,e,11;10,e,0;10,e,8;11,e,17;12,m,13;13,e,12;13,e,15;14,e,12;14,e,15;15,e,17;16,e,10;16,e,14;17,e,16;17,e,19;18,e,16;18,e,19#18#19", regExToNfa.toString());
	}

	@Test
	public void testRegEx10() {
		RegExToNfa regExToNfa= new RegExToNfa("a;h#hha*.*h|**|");
		assertEquals("0;1;2;3;4;5;7;8;9;10;11;12;13;14;15;16;17;18;19#a;h#0,h,1;1,e,19;2,h,3;3,e,4;3,e,7;4,a,5;5,e,4;5,e,7;7,e,2;7,e,9;8,e,2;8,e,9;9,e,13;10,h,11;11,e,13;12,e,8;12,e,10;13,e,12;13,e,15;14,e,12;14,e,15;15,e,14;15,e,17;16,e,14;16,e,17;17,e,19;18,e,0;18,e,16#18#19", regExToNfa.toString());
	}

}