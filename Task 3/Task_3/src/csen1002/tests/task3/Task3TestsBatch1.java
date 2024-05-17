package csen1002.tests.task3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task3.FallbackDfa;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task3TestsBatch1 {

	@Test
	public void testFallbackDfa1String1() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#l;n;v#0,l,0;0,n,1;0,v,4;1,l,6;1,n,5;1,v,3;2,l,8;2,n,8;2,v,13;3,l,1;3,n,3;3,v,7;4,l,14;4,n,4;4,v,3;5,l,7;5,n,13;5,v,10;6,l,6;6,n,12;6,v,7;7,l,3;7,n,8;7,v,8;8,l,3;8,n,11;8,v,7;9,l,0;9,n,11;9,v,9;10,l,3;10,n,0;10,v,14;11,l,2;11,n,14;11,v,13;12,l,0;12,n,13;12,v,5;13,l,5;13,n,14;13,v,9;14,l,13;14,n,2;14,v,3#6#4;7;13");
		assertEquals("vlvvnnlln,13;lv,7;v,7;nn,13;nn,13", fallbackDfa.run("vlvvnnllnlvvnnnn"));
	}

	@Test
	public void testFallbackDfa1String2() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#l;n;v#0,l,0;0,n,1;0,v,4;1,l,6;1,n,5;1,v,3;2,l,8;2,n,8;2,v,13;3,l,1;3,n,3;3,v,7;4,l,14;4,n,4;4,v,3;5,l,7;5,n,13;5,v,10;6,l,6;6,n,12;6,v,7;7,l,3;7,n,8;7,v,8;8,l,3;8,n,11;8,v,7;9,l,0;9,n,11;9,v,9;10,l,3;10,n,0;10,v,14;11,l,2;11,n,14;11,v,13;12,l,0;12,n,13;12,v,5;13,l,5;13,n,14;13,v,9;14,l,13;14,n,2;14,v,3#6#4;7;13");
		assertEquals("nn,13;lv,7;v,7;nn,13;nn,13", fallbackDfa.run("nnlvvnnnn"));
	}

	@Test
	public void testFallbackDfa1String3() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#l;n;v#0,l,0;0,n,1;0,v,4;1,l,6;1,n,5;1,v,3;2,l,8;2,n,8;2,v,13;3,l,1;3,n,3;3,v,7;4,l,14;4,n,4;4,v,3;5,l,7;5,n,13;5,v,10;6,l,6;6,n,12;6,v,7;7,l,3;7,n,8;7,v,8;8,l,3;8,n,11;8,v,7;9,l,0;9,n,11;9,v,9;10,l,3;10,n,0;10,v,14;11,l,2;11,n,14;11,v,13;12,l,0;12,n,13;12,v,5;13,l,5;13,n,14;13,v,9;14,l,13;14,n,2;14,v,3#6#4;7;13");
		assertEquals("nlvlnv,13;v,7;v,7;nlnn,5", fallbackDfa.run("nlvlnvvvnlnn"));
	}

	@Test
	public void testFallbackDfa1String4() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#l;n;v#0,l,0;0,n,1;0,v,4;1,l,6;1,n,5;1,v,3;2,l,8;2,n,8;2,v,13;3,l,1;3,n,3;3,v,7;4,l,14;4,n,4;4,v,3;5,l,7;5,n,13;5,v,10;6,l,6;6,n,12;6,v,7;7,l,3;7,n,8;7,v,8;8,l,3;8,n,11;8,v,7;9,l,0;9,n,11;9,v,9;10,l,3;10,n,0;10,v,14;11,l,2;11,n,14;11,v,13;12,l,0;12,n,13;12,v,5;13,l,5;13,n,14;13,v,9;14,l,13;14,n,2;14,v,3#6#4;7;13");
		assertEquals("nvnlnnvnv,7;v,7;lnlnvv,7;nlnlln,12", fallbackDfa.run("nvnlnnvnvvlnlnvvnlnlln"));
	}

	@Test
	public void testFallbackDfa1String5() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#l;n;v#0,l,0;0,n,1;0,v,4;1,l,6;1,n,5;1,v,3;2,l,8;2,n,8;2,v,13;3,l,1;3,n,3;3,v,7;4,l,14;4,n,4;4,v,3;5,l,7;5,n,13;5,v,10;6,l,6;6,n,12;6,v,7;7,l,3;7,n,8;7,v,8;8,l,3;8,n,11;8,v,7;9,l,0;9,n,11;9,v,9;10,l,3;10,n,0;10,v,14;11,l,2;11,n,14;11,v,13;12,l,0;12,n,13;12,v,5;13,l,5;13,n,14;13,v,9;14,l,13;14,n,2;14,v,3#6#4;7;13");
		assertEquals("nvvnvnvvnv,7;v,7;lnn,13;nnln,13", fallbackDfa.run("nvvnvnvvnvvlnnnnln"));
	}

	@Test
	public void testFallbackDfa2String1() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#g;h;k#0,g,10;0,h,7;0,k,13;1,g,12;1,h,7;1,k,7;2,g,14;2,h,0;2,k,11;3,g,14;3,h,9;3,k,13;4,g,7;4,h,8;4,k,5;5,g,14;5,h,0;5,k,3;6,g,7;6,h,11;6,k,5;7,g,1;7,h,4;7,k,11;8,g,3;8,h,6;8,k,2;9,g,14;9,h,7;9,k,1;10,g,2;10,h,6;10,k,3;11,g,7;11,h,10;11,k,5;12,g,11;12,h,7;12,k,8;13,g,2;13,h,6;13,k,14;14,g,14;14,h,11;14,k,5#11#5;6");
		assertEquals("kghk,5;k,5;k,5;k,5", fallbackDfa.run("kghkkkk"));
	}

	@Test
	public void testFallbackDfa2String2() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#g;h;k#0,g,10;0,h,7;0,k,13;1,g,12;1,h,7;1,k,7;2,g,14;2,h,0;2,k,11;3,g,14;3,h,9;3,k,13;4,g,7;4,h,8;4,k,5;5,g,14;5,h,0;5,k,3;6,g,7;6,h,11;6,k,5;7,g,1;7,h,4;7,k,11;8,g,3;8,h,6;8,k,2;9,g,14;9,h,7;9,k,1;10,g,2;10,h,6;10,k,3;11,g,7;11,h,10;11,k,5;12,g,11;12,h,7;12,k,8;13,g,2;13,h,6;13,k,14;14,g,14;14,h,11;14,k,5#11#5;6");
		assertEquals("kgkghk,5;k,5;hh,6;g,7", fallbackDfa.run("kgkghkkhhg"));
	}

	@Test
	public void testFallbackDfa2String3() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#g;h;k#0,g,10;0,h,7;0,k,13;1,g,12;1,h,7;1,k,7;2,g,14;2,h,0;2,k,11;3,g,14;3,h,9;3,k,13;4,g,7;4,h,8;4,k,5;5,g,14;5,h,0;5,k,3;6,g,7;6,h,11;6,k,5;7,g,1;7,h,4;7,k,11;8,g,3;8,h,6;8,k,2;9,g,14;9,h,7;9,k,1;10,g,2;10,h,6;10,k,3;11,g,7;11,h,10;11,k,5;12,g,11;12,h,7;12,k,8;13,g,2;13,h,6;13,k,14;14,g,14;14,h,11;14,k,5#11#5;6");
		assertEquals("hkgghk,5;k,5;k,5;k,5", fallbackDfa.run("hkgghkkkk"));
	}

	@Test
	public void testFallbackDfa2String4() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#g;h;k#0,g,10;0,h,7;0,k,13;1,g,12;1,h,7;1,k,7;2,g,14;2,h,0;2,k,11;3,g,14;3,h,9;3,k,13;4,g,7;4,h,8;4,k,5;5,g,14;5,h,0;5,k,3;6,g,7;6,h,11;6,k,5;7,g,1;7,h,4;7,k,11;8,g,3;8,h,6;8,k,2;9,g,14;9,h,7;9,k,1;10,g,2;10,h,6;10,k,3;11,g,7;11,h,10;11,k,5;12,g,11;12,h,7;12,k,8;13,g,2;13,h,6;13,k,14;14,g,14;14,h,11;14,k,5#11#5;6");
		assertEquals("k,5;khkghgh,6;hhk,5;k,5;g,7", fallbackDfa.run("kkhkghghhhkkg"));
	}

	@Test
	public void testFallbackDfa2String5() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14#g;h;k#0,g,10;0,h,7;0,k,13;1,g,12;1,h,7;1,k,7;2,g,14;2,h,0;2,k,11;3,g,14;3,h,9;3,k,13;4,g,7;4,h,8;4,k,5;5,g,14;5,h,0;5,k,3;6,g,7;6,h,11;6,k,5;7,g,1;7,h,4;7,k,11;8,g,3;8,h,6;8,k,2;9,g,14;9,h,7;9,k,1;10,g,2;10,h,6;10,k,3;11,g,7;11,h,10;11,k,5;12,g,11;12,h,7;12,k,8;13,g,2;13,h,6;13,k,14;14,g,14;14,h,11;14,k,5#11#5;6");
		assertEquals("hhhkhghhkghk,5;k,5;k,5;k,5", fallbackDfa.run("hhhkhghhkghkkkk"));
	}

}