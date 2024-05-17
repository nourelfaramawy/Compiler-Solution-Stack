package csen1002.tests.task4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task4.CfgEpsUnitElim;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task4TestsBatch1 {

	@Test
	public void testCfgEpsilonRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;I;M;A;V#g;p;w#S/SpM,gAM,pI;I/A,ApVV,M,S,e,gIMIV,wIVAM;M/A,M,MgAS,e,gVIAp,w;A/A,M,MMA,e,gV,pVwAA;V/MgV,MpA,gAIA");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;I;M;A;V#g;p;w#S/Sp,SpM,g,gA,gAM,gM,p,pI;I/A,ApVV,M,S,gIIV,gIMIV,gIMV,gIV,gMIV,gMV,gV,pVV,wIV,wIVA,wIVAM,wIVM,wV,wVA,wVAM,wVM;M/A,M,MgAS,MgS,gAS,gS,gVAp,gVIAp,gVIp,gVp,w;A/A,M,MA,MM,MMA,gV,pVw,pVwA,pVwAA;V/MgV,Mp,MpA,g,gA,gAA,gAI,gAIA,gI,gIA,gV,p,pA", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;U;D;Y;N;F;G#l;p;r;s;v#S/SGpF,YGUsS,l,sNNSS;U/GSFl,U,e,vNrFU;D/D,NDG,S,SrUFl,pUsNp;Y/DGpSS,N,SFY,s;N/D,DpSU,FYv,S,e;F/D,S,e,rF;G/FDSDp,FSrN,GY,p");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;U;D;Y;N;F;G#l;p;r;s;v#S/GUsS,GsS,SGp,SGpF,YGUsS,YGsS,l,sNNSS,sNSS,sSS;U/GSFl,GSl,U,vNr,vNrF,vNrFU,vNrU,vr,vrF,vrFU,vrU;D/D,DG,NDG,S,SrFl,SrUFl,SrUl,Srl,pUsNp,pUsp,psNp,psp;Y/DGpSS,N,S,SF,SFY,SY,s;N/D,DpS,DpSU,FYv,Fv,S,Yv,v;F/D,S,r,rF;G/DSDp,FDSDp,FSr,FSrN,G,GY,Sr,SrN,p", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;M;G;E;A;V#a;j;l;m;u#S/GGuV,VlV,uAu;M/AuMM,M,S,VA,e,mMj;G/GEjVS,M,e,jG,lVM;E/E,G,e,jS,l;A/E,M,VMAaG;V/AV,SSmGG,VmG");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;M;G;E;A;V#a;j;l;m;u#S/GGuV,GuV,VlV,uAu,uV,uu;M/Au,AuM,AuMM,M,S,V,VA,mMj,mj,u,uM,uMM;G/EjVS,GEjVS,GjVS,M,j,jG,jVS,lV,lVM;E/E,G,jS,l;A/E,M,VAa,VAaG,VMAa,VMAaG,VMa,VMaG,Va,VaG;V/AV,SSm,SSmG,SSmGG,V,Vm,VmG", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;W;B;J;K;Y#a;i;p#S/B,BKYYp,S,pBSK;W/JJaS,YaWY,iBWJ,iYWaJ;B/JSB,K,WJYKB,YiK,pWpBi;J/aBKi,p;K/B,S,Sa,aYYpJ,iBKWY,pYi;Y/K,S,Yi");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;W;B;J;K;Y#a;i;p#S/BKYYp,JSB,Sa,WJYKB,YiK,aYYpJ,iBKWY,pBSK,pWpBi,pYi;W/JJaS,YaWY,iBWJ,iYWaJ;B/BKYYp,JSB,Sa,WJYKB,YiK,aYYpJ,iBKWY,pBSK,pWpBi,pYi;J/aBKi,p;K/BKYYp,JSB,Sa,WJYKB,YiK,aYYpJ,iBKWY,pBSK,pWpBi,pYi;Y/BKYYp,JSB,Sa,WJYKB,Yi,YiK,aYYpJ,iBKWY,pBSK,pWpBi,pYi", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;W;M;G;C;P;V#a;l;p;t#S/G,M,SP,V;W/MGt,Sp,pSaW;M/G,pCpV;G/GVa,PWG,V,aC;C/G,GWSaG,Gt;P/GlP,M,P,W,lP;V/C,WG,WGa,aC");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;W;M;G;C;P;V#a;l;p;t#S/GVa,GWSaG,Gt,PWG,SP,WG,WGa,aC,pCpV;W/MGt,Sp,pSaW;M/GVa,GWSaG,Gt,PWG,WG,WGa,aC,pCpV;G/GVa,GWSaG,Gt,PWG,WG,WGa,aC;C/GVa,GWSaG,Gt,PWG,WG,WGa,aC;P/GVa,GWSaG,GlP,Gt,MGt,PWG,Sp,WG,WGa,aC,lP,pCpV,pSaW;V/GVa,GWSaG,Gt,PWG,WG,WGa,aC", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;Q;W;L;C;E;B#n;u;y#S/E,LEW,WS,n,yW;Q/E,S,WCnEW;W/B,ySn;L/E,WQnCE,nQ,y;C/LQ,QLnQ,W,u;E/n,ySC;B/n,uLnBn");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;Q;W;L;C;E;B#n;u;y#S/LEW,WS,n,ySC,yW;Q/LEW,WCnEW,WS,n,ySC,yW;W/n,uLnBn,ySn;L/WQnCE,n,nQ,y,ySC;C/LQ,QLnQ,n,u,uLnBn,ySn;E/n,ySC;B/n,uLnBn", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;U;I;D;C;L;Q#g;h;k;t;y#S/StShI,UtQ;U/D,IS,S,UyDCy,e,gCkD;I/CQU,LtIhL,SL,Uh;D/I,LQ,UyCgL,tI;C/ISIgI,StCg,UkIDt,k;L/DCkQ,S,e,gIIUL;Q/I,gUUSC,y");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;U;I;D;C;L;Q#g;h;k;t;y#S/StShI,UtQ,tQ;U/CQ,CQU,IS,LQ,LtIh,LtIhL,SL,StShI,Uh,UtQ,UyCg,UyCgL,UyDCy,gCkD,gSC,gUSC,gUUSC,h,tI,tIh,tIhL,tQ,y,yCg,yCgL,yDCy;I/CQ,CQU,LtIh,LtIhL,SL,StShI,Uh,UtQ,h,tIh,tIhL,tQ;D/CQ,CQU,LQ,LtIh,LtIhL,SL,StShI,Uh,UtQ,UyCg,UyCgL,gSC,gUSC,gUUSC,h,tI,tIh,tIhL,tQ,y,yCg,yCgL;C/ISIgI,StCg,UkIDt,k,kIDt;L/DCkQ,StShI,UtQ,gII,gIIL,gIIU,gIIUL,tQ;Q/CQ,CQU,LtIh,LtIhL,SL,StShI,Uh,UtQ,gSC,gUSC,gUUSC,h,tIh,tIhL,tQ,y", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;H;Y;L;V;A;X#k;n;o;w#S/SALkA,V,XHYn,kAV,w;H/H,LY,VYV,kH;Y/AnV,LV,V,e;L/HLnH,HXLY,SwXH,e;V/HnYA,nLSo,nXAw,o;A/HAo,VoXkA,YVV,Yn,e;X/HHY,HHoS");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;H;Y;L;V;A;X#k;n;o;w#S/HYn,Hn,HnA,HnY,HnYA,SALk,SALkA,SAk,SAkA,SLk,SLkA,Sk,SkA,XHYn,XHn,XYn,Xn,Yn,kAV,kV,n,nA,nAw,nLSo,nSo,nXAw,nXw,nY,nYA,nw,o,w;H/AnV,HH,HHY,HHoS,HL,HLY,HLn,HLnH,HX,HXL,HXLY,HXY,HY,Hn,HnA,HnH,HnY,HnYA,HoS,LV,LY,Ln,LnH,Sw,SwH,SwX,SwXH,VV,VYV,XL,XLY,XY,k,kH,n,nA,nAw,nH,nLSo,nSo,nV,nXAw,nXw,nY,nYA,nw,o,oS;Y/AnV,Hn,HnA,HnY,HnYA,LV,n,nA,nAw,nLSo,nSo,nV,nXAw,nXw,nY,nYA,nw,o;L/AnV,HH,HHY,HHoS,HL,HLY,HLn,HLnH,HX,HXL,HXLY,HXY,HY,Hn,HnA,HnH,HnY,HnYA,HoS,LV,LY,Ln,LnH,Sw,SwH,SwX,SwXH,VV,VYV,XL,XLY,XY,k,kH,n,nA,nAw,nH,nLSo,nSo,nV,nXAw,nXw,nY,nYA,nw,o,oS;V/Hn,HnA,HnY,HnYA,n,nA,nAw,nLSo,nSo,nXAw,nXw,nY,nYA,nw,o;A/Ao,HAo,Ho,VV,VoXk,VoXkA,Vok,VokA,YVV,Yn,n,o;X/AnV,HH,HHY,HHoS,HL,HLY,HLn,HLnH,HX,HXL,HXLY,HXY,HY,Hn,HnA,HnH,HnY,HnYA,HoS,LV,LY,Ln,LnH,Sw,SwH,SwX,SwXH,VV,VYV,XL,XLY,XY,k,kH,n,nA,nAw,nH,nLSo,nSo,nV,nXAw,nXw,nY,nYA,nw,o,oS", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;P;J;C;N;K#o;s;t;w#S/Nt,SwK,oKCN,sC;P/K,P,e,wKCoS;J/JN,KStS,P,S,e,sS;C/NCCs,tStN,wJS;N/N,S,SSJ,wKCoJ;K/o,oSoP,oSwK");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;P;J;C;N;K#o;s;t;w#S/Nt,SwK,oKCN,sC;P/o,oSo,oSoP,oSwK,wKCoS;J/JN,KStS,Nt,SS,SSJ,SwK,o,oKCN,oSo,oSoP,oSwK,sC,sS,wKCo,wKCoJ,wKCoS;C/NCCs,tStN,wJS,wS;N/Nt,SS,SSJ,SwK,oKCN,sC,wKCo,wKCoJ;K/o,oSo,oSoP,oSwK", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination4() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;O;T;K;P;G;B#f;i;p;z#S/BiKfT,Gz,pKG,pKTK;O/GKTBG,P,PK,e,zP;T/O,SzP,T,pK;K/BKSS,zTB;P/BPB,TzOiO,e;G/G,O,OPOT,e;B/GfGGz,KTPPS,iOK");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;O;T;K;P;G;B#f;i;p;z#S/BiKf,BiKfT,Gz,pK,pKG,pKK,pKTK,z;O/BB,BKSS,BPB,GKB,GKBG,GKTB,GKTBG,KB,KBG,KTB,KTBG,PK,TzOi,TzOiO,Tzi,TziO,z,zB,zOi,zOiO,zP,zTB,zi,ziO;T/BB,BKSS,BPB,GKB,GKBG,GKTB,GKTBG,KB,KBG,KTB,KTBG,PK,Sz,SzP,TzOi,TzOiO,Tzi,TziO,pK,z,zB,zOi,zOiO,zP,zTB,zi,ziO;K/BKSS,zB,zTB;P/BB,BPB,TzOi,TzOiO,Tzi,TziO,zOi,zOiO,zi,ziO;G/BB,BKSS,BPB,GKB,GKBG,GKTB,GKTBG,KB,KBG,KTB,KTBG,OO,OOT,OP,OPO,OPOT,OPT,OT,PK,PO,POT,PT,Sz,SzP,TzOi,TzOiO,Tzi,TziO,pK,z,zB,zOi,zOiO,zP,zTB,zi,ziO;B/GfGGz,GfGz,Gfz,KPPS,KPS,KS,KTPPS,KTPS,KTS,fGGz,fGz,fz,iK,iOK", cfgEpsUnitElim.toString());
	}

}