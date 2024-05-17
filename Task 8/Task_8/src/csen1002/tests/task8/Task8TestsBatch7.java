package csen1002.tests.task8;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task8.CfgLl1Parser;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task8TestsBatch7 {

	@Test
	public void testCfg1String1() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;F;V;M;N#c;j;k;n;t;w;z#S/t,FFNn;F/nVzS,c;V/k,nMNz,e;M/t,jS,e;N/zF,wVt#S/t,cn;F/n,c;V/k,n,e;M/t,j,e;N/z,w#S/$cnwz;F/cnz;V/tz;M/wz;N/nz");
		assertEquals("S;FFNn;cFNn;ccNn;ccwVtn;ccwnMNztn;ccwnjSNztn;ccwnjFFNnNztn;ccwnjcFNnNztn;ERROR", cfgLl1Parser.parse("ccwnjcwt"));
	}

	@Test
	public void testCfg1String2() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;F;V;M;N#c;j;k;n;t;w;z#S/t,FFNn;F/nVzS,c;V/k,nMNz,e;M/t,jS,e;N/zF,wVt#S/t,cn;F/n,c;V/k,n,e;M/t,j,e;N/z,w#S/$cnwz;F/cnz;V/tz;M/wz;N/nz");
		assertEquals("S;FFNn;nVzSFNn;nzSFNn;nzFFNnFNn;nzcFNnFNn;nzccNnFNn;ERROR", cfgLl1Parser.parse("nzccjknn"));
	}

	@Test
	public void testCfg1String3() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;F;V;M;N#c;j;k;n;t;w;z#S/t,FFNn;F/nVzS,c;V/k,nMNz,e;M/t,jS,e;N/zF,wVt#S/t,cn;F/n,c;V/k,n,e;M/t,j,e;N/z,w#S/$cnwz;F/cnz;V/tz;M/wz;N/nz");
		assertEquals("S;FFNn;cFNn;ccNn;cczFn;ccznVzSn;ccznkzSn;ccznkztn", cfgLl1Parser.parse("ccznkztn"));
	}

	@Test
	public void testCfg1String4() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;F;V;M;N#c;j;k;n;t;w;z#S/t,FFNn;F/nVzS,c;V/k,nMNz,e;M/t,jS,e;N/zF,wVt#S/t,cn;F/n,c;V/k,n,e;M/t,j,e;N/z,w#S/$cnwz;F/cnz;V/tz;M/wz;N/nz");
		assertEquals("S;FFNn;nVzSFNn;nnMNzzSFNn;nnNzzSFNn;nnwVtzzSFNn;nnwnMNztzzSFNn;nnwnjSNztzzSFNn;ERROR", cfgLl1Parser.parse("nnwnjzk"));
	}

	@Test
	public void testCfg1String5() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;F;V;M;N#c;j;k;n;t;w;z#S/t,FFNn;F/nVzS,c;V/k,nMNz,e;M/t,jS,e;N/zF,wVt#S/t,cn;F/n,c;V/k,n,e;M/t,j,e;N/z,w#S/$cnwz;F/cnz;V/tz;M/wz;N/nz");
		assertEquals("S;FFNn;nVzSFNn;nzSFNn;nzFFNnFNn;nzcFNnFNn;nzccNnFNn;nzccwVtnFNn;nzccwtnFNn;nzccwtncNn;nzccwtnczFn;nzccwtnczcn", cfgLl1Parser.parse("nzccwtnczcn"));
	}

	@Test
	public void testCfg2String1() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;M;R;Q;O;P;Y#c;f;h;k;l;q;w#S/Y,wS,c;M/fQRS,hOqM,e;R/hP,wQqY;Q/l,Y;O/cQ,Pk,hP,lR,e;P/k,w;Y/fRMl,k#S/fk,w,c;M/f,h,e;R/h,w;Q/l,fk;O/c,kw,h,l,e;P/k,w;Y/f,k#S/$l;M/l;R/cfhklqw;Q/hqw;O/q;P/cfhklqw;Y/$cfhklqw");
		assertEquals("S;Y;fRMl;fwQqYMl;fwYqYMl;fwfRMlqYMl;fwfhPMlqYMl;ERROR", cfgLl1Parser.parse("fwfhqwhw"));
	}

	@Test
	public void testCfg2String2() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;M;R;Q;O;P;Y#c;f;h;k;l;q;w#S/Y,wS,c;M/fQRS,hOqM,e;R/hP,wQqY;Q/l,Y;O/cQ,Pk,hP,lR,e;P/k,w;Y/fRMl,k#S/fk,w,c;M/f,h,e;R/h,w;Q/l,fk;O/c,kw,h,l,e;P/k,w;Y/f,k#S/$l;M/l;R/cfhklqw;Q/hqw;O/q;P/cfhklqw;Y/$cfhklqw");
		assertEquals("S;wS;wY;wfRMl;wfwQqYMl;wfwYqYMl;wfwfRMlqYMl;ERROR", cfgLl1Parser.parse("wfwflcck"));
	}

	@Test
	public void testCfg2String3() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;M;R;Q;O;P;Y#c;f;h;k;l;q;w#S/Y,wS,c;M/fQRS,hOqM,e;R/hP,wQqY;Q/l,Y;O/cQ,Pk,hP,lR,e;P/k,w;Y/fRMl,k#S/fk,w,c;M/f,h,e;R/h,w;Q/l,fk;O/c,kw,h,l,e;P/k,w;Y/f,k#S/$l;M/l;R/cfhklqw;Q/hqw;O/q;P/cfhklqw;Y/$cfhklqw");
		assertEquals("S;Y;fRMl;fwQqYMl;fwYqYMl;fwfRMlqYMl;fwfhPMlqYMl;fwfhwMlqYMl;fwfhwlqYMl;ERROR", cfgLl1Parser.parse("fwfhwlkw"));
	}

	@Test
	public void testCfg2String4() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;M;R;Q;O;P;Y#c;f;h;k;l;q;w#S/Y,wS,c;M/fQRS,hOqM,e;R/hP,wQqY;Q/l,Y;O/cQ,Pk,hP,lR,e;P/k,w;Y/fRMl,k#S/fk,w,c;M/f,h,e;R/h,w;Q/l,fk;O/c,kw,h,l,e;P/k,w;Y/f,k#S/$l;M/l;R/cfhklqw;Q/hqw;O/q;P/cfhklqw;Y/$cfhklqw");
		assertEquals("S;Y;fRMl;fwQqYMl;fwlqYMl;fwlqfRMlMl;fwlqfhPMlMl;fwlqfhkMlMl;fwlqfhklMl;fwlqfhkll", cfgLl1Parser.parse("fwlqfhkll"));
	}

	@Test
	public void testCfg2String5() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;M;R;Q;O;P;Y#c;f;h;k;l;q;w#S/Y,wS,c;M/fQRS,hOqM,e;R/hP,wQqY;Q/l,Y;O/cQ,Pk,hP,lR,e;P/k,w;Y/fRMl,k#S/fk,w,c;M/f,h,e;R/h,w;Q/l,fk;O/c,kw,h,l,e;P/k,w;Y/f,k#S/$l;M/l;R/cfhklqw;Q/hqw;O/q;P/cfhklqw;Y/$cfhklqw");
		assertEquals("S;Y;fRMl;fhPMl;fhkMl;fhkhOqMl;fhkhqMl;fhkhqhOqMl;fhkhqhPkqMl;fhkhqhkkqMl;fhkhqhkkql", cfgLl1Parser.parse("fhkhqhkkql"));
	}

}