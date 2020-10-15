// Vuex configuration
import Vue from 'vue'
import Vuex from 'vuex';

Vue.use(Vuex);

const getDefaultState = ()=>{
    return {
        currentUser:{
            id:null,
            email: null,
            token: null,
            roles:[],
            sections:[],
            fio:''
        }
    }
};

export {getDefaultState};

export const store = new Vuex.Store({
    state: {
        currentUser:{
            id:null,
            email: null,
            token: null,
            roles:[

                ],
            sections:[],
            fio:null
        },
        publicData:{
            allSections:[
            ],
            allDegrees:[

            ],
            allRoles:[]
        }
    },
    getters: {
        ID: state => state.currentUser.id,
        USER: state => {
            if((state.currentUser.token === null) &&  (state.currentUser.email === null)){
                return null
            }else
                return state.currentUser;
        },
        EMAIL: state => state.currentUser.email,
        TOKEN: state => state.currentUser.token,
        ROLES:state => state.currentUser.roles,
        SECTIONS:state => state.currentUser.sections,
        FIO:state => state.currentUser.fio,
        ALL_SECTIONS: state => state.publicData.allSections,
        ALL_DEGREES: state => state.publicData.allDegrees,
        ALL_ROLES: state => state.publicData.allRoles
    },
    mutations: {
        RESET_STATE (state) {
            // acquire initial state
            const s = getDefaultState();
            Object.keys(s).forEach(key => {
                state[key] = s[key]
            })
        },
        SET_EMAIL: (state,payload) => {
            state.currentUser.email = payload;
        },
        SET_TOKEN: (state,payload) => {
            state.currentUser.token = payload;
        },
        SET_ROLES: (state, payload) =>
        {
            if (Array.isArray( payload)){
                const arr = [];
                payload.forEach(p=>arr.push(p));
                state.currentUser.roles=arr;
            }

        },
        SET_SECTIONS: (state, payload) => {
            if (Array.isArray( payload)) {
                const arr = [];
                payload.forEach(p => arr.push(p));
                state.currentUser.sections = arr;
            }
        },
        SET_ID:(state, payload) => {
            state.currentUser.id = payload;
        },
        SET_FIO: (state, payload) => {
            state.currentUser.fio = payload
        },
        SET_ALL_ROLES:(state, payload) =>{
            state.allRoles=[];

            if(Array.isArray(payload)){
                state.publicData.allRoles=payload;
            }
        },
        SET_ALL_DEGREES:(state, payload) =>{
            state.allDegree=[];

            if(Array.isArray(payload)){
              state.publicData.allDegrees = payload;
            }
        },
        SET_ALL_SECTIONS:(state, payload) =>{
            state.allSections=[];

            if(Array.isArray(payload)){
                state.publicData.allSections  = payload;
            }
        }

        // REMOVE_ROLE: (state, payload) => {
        //     state.currentUser.roles = state.currentUser.roles.filter(r=>r!==payload);
        // }
    },
    actions: {

    },
});
