import Vue from "vue";
import Router from "vue-router";
import Home from "./components/Home";
import LogIn from "./components/LogIn";
import Registration from "./components/Registration";
import ManageInfo from "./components/manageInfo/ManageInfo";
import ConfereeMain from "./components/reports/conferee/ConfereeMain";
import AdminMain from "./components/admin/AdminMain";
import ReviewerMain from "./components/reports/reviewer/ReviewerMain";
import RedactorMain from "./components/reports/redactor/RedactorMain";
import Archive from "./components/Archive";



Vue.use(Router);

export default new Router( {
   routes: [
       {
           path:'/',
           name: 'home',
           component: Home,
           alias:'/home'
       },
       {
           path:
               '/login',
           name: 'login',
           component: LogIn
       },
       {
           path:
               '/registration',
           name: 'registration',
           component: Registration
       },
       {
           path:'/admin',
           name:'registration',
           component: AdminMain
       },
       {
           path:
               '/manageInfo',
           name: 'manageInfo',
           component: ManageInfo
       },
       {
           path: '/conferee',
           name:'conferee',
           component: ConfereeMain
       },
       {
           path:'/reviewer',
           name:'reviewer',
           component: ReviewerMain
       },
       {
           path:'/redactor',
           name:'redactor',
           component: RedactorMain
       },
       {
           path:'/archive',
           name:'archvie',
           component: Archive
       }

   ]
})