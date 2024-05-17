package csen1002.tests.task6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task6.CfgFirstFollow;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task6TestsBatch7 {

	@Test
	public void testCfg1First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;D;X;U;K;A;Y#c;g;h;k;l;m;v#S/lAYg,mUScD;D/gKk,cUSc,e,D;X/KDlD,Sk,gX,YDYkD;U/Y,mYDY,XcScK,X;K/hK,g,e,A;A/UYSvA,KA,XvUK,K,A;Y/SUY,X,YYUg,gSl,A,XSSl");
		assertEquals("S/lm;D/ceg;X/cghklm;U/ceghklm;K/ceghklm;A/ceghklm;Y/ceghklm", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg1Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;D;X;U;K;A;Y#c;g;h;k;l;m;v#S/lAYg,mUScD;D/gKk,cUSc,e,D;X/KDlD,Sk,gX,YDYkD;U/Y,mYDY,XcScK,X;K/hK,g,e,A;A/UYSvA,KA,XvUK,K,A;Y/SUY,X,YYUg,gSl,A,XSSl");
		assertEquals("S/$cghklmv;D/$cghklmv;X/cghklmv;U/cghklm;K/cghklm;A/cghklm;Y/cghklm", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg2First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;F;Y;B;R;Z;U#c;j;m;s;t;u;y#S/yU,sZsU,Sj;F/yZ,BFSu,F;Y/c,Su,B;B/y,cBt,e,B;R/Zt,RcFR,m,uS,S,s,e,Z;Z/m,sY,yZSUS,tSU,BFFF,sBjYZ,Z,Y;U/RYyU,sYj,yYRUB");
		assertEquals("S/sy;F/cy;Y/cesy;B/cey;R/cemstuy;Z/cemsty;U/cmstuy", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg2Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;F;Y;B;R;Z;U#c;j;m;s;t;u;y#S/yU,sZsU,Sj;F/yZ,BFSu,F;Y/c,Su,B;B/y,cBt,e,B;R/Zt,RcFR,m,uS,S,s,e,Z;Z/m,sY,yZSUS,tSU,BFFF,sBjYZ,Z,Y;U/RYyU,sYj,yYRUB");
		assertEquals("S/$cjmstuy;F/cmstuy;Y/cjmstuy;B/$cjmstuy;R/cmstuy;Z/cmstuy;U/$cjmstuy", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg3First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;F;M;Q;V#f;h;i;l;m;o;q#S/hF,Fq,VhQ,SoQmQ,F;F/lM,l,e,F,M;M/mFh,lM,e;Q/FQfF,mVh,MQ,Q,F;V/oF,SM,lMQ,S,VFiMl");
		assertEquals("S/ehilmoq;F/elm;M/elm;Q/eflm;V/ehilmoq", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg3Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;F;M;Q;V#f;h;i;l;m;o;q#S/hF,Fq,VhQ,SoQmQ,F;F/lM,l,e,F,M;M/mFh,lM,e;Q/FQfF,mVh,MQ,Q,F;V/oF,SM,lMQ,S,VFiMl");
		assertEquals("S/$hilmo;F/$fhilmoq;M/$fhilmoq;Q/$fhilmo;V/hilm", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg4First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;B;I;T;Q;D#h;l;r;t;u;x#S/r,Qr,Q;B/l,TSBQT;I/x,Q,e;T/TQrSh,uDlT,SQt,QStI,I,T;Q/tQDu,rDxQr,e,I;D/BxDQ,uTI,QDIT,BIlIS,Q,IrI");
		assertEquals("S/ertx;B/lrtux;I/ertx;T/ertux;Q/ertx;D/elrtux", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg4Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;B;I;T;Q;D#h;l;r;t;u;x#S/r,Qr,Q;B/l,TSBQT;I/x,Q,e;T/TQrSh,uDlT,SQt,QStI,I,T;Q/tQDu,rDxQr,e,I;D/BxDQ,uTI,QDIT,BIlIS,Q,IrI");
		assertEquals("S/$hlrtux;B/lrtux;I/$hlrtux;T/lrtux;Q/$hlrtux;D/lrtux", cfgFirstFollow.follow());
	}

	@Test
	public void testCfg5First() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;T;K;Y;B;E;Z#i;k;l;o;t#S/kB,kBY,S,Y;T/l,TT,ZiY,e,E,T;K/kEBk,lKZSi,SlEl,e;Y/Sl,iY,e;B/K,iBKoS,KS,El,S;E/ESkTk,k,kKEY,T;Z/l,tZlE,TYkE");
		assertEquals("S/eikl;T/eiklt;K/eikl;Y/eikl;B/eiklt;E/eiklt;Z/iklt", cfgFirstFollow.first());
	}
	
	@Test
	public void testCfg5Follow() {
		CfgFirstFollow cfgFirstFollow= new CfgFirstFollow("S;T;K;Y;B;E;Z#i;k;l;o;t#S/kB,kBY,S,Y;T/l,TT,ZiY,e,E,T;K/kEBk,lKZSi,SlEl,e;Y/Sl,iY,e;B/K,iBKoS,KS,El,S;E/ESkTk,k,kKEY,T;Z/l,tZlE,TYkE");
		assertEquals("S/$iklo;T/iklt;K/$iklot;Y/$iklot;B/$iklo;E/iklt;Z/ikl", cfgFirstFollow.follow());
	}

}