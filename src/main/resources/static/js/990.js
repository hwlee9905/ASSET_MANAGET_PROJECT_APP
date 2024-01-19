"use strict";(self["webpackChunkams"]=self["webpackChunkams"]||[]).push([[990],{8990:function(t,e,n){n.r(e),n.d(e,{createSwipeBackGesture:function(){return c}});var r=n(6587),s=n(545),a=n(6515);
/*!
 * (C) Ionic http://ionicframework.com - MIT License
 */
const c=(t,e,n,c,i)=>{const o=t.ownerDocument.defaultView;let u=(0,s.i)(t);const h=t=>{const e=50,{startX:n}=t;return u?n>=o.innerWidth-e:n<=e},l=t=>u?-t.deltaX:t.deltaX,d=t=>u?-t.velocityX:t.velocityX,f=n=>(u=(0,s.i)(t),h(n)&&e()),k=t=>{const e=l(t),n=e/o.innerWidth;c(n)},w=t=>{const e=l(t),n=o.innerWidth,s=e/n,a=d(t),c=n/2,u=a>=0&&(a>.2||e>c),h=u?1-s:s,f=h*n;let k=0;if(f>5){const t=f/Math.abs(a);k=Math.min(t,540)}i(u,s<=0?.01:(0,r.h)(0,s,.9999),k)};return(0,a.createGesture)({el:t,gestureName:"goback-swipe",gesturePriority:40,threshold:10,canStart:f,onStart:n,onMove:k,onEnd:w})}}}]);
//# sourceMappingURL=990.js.map