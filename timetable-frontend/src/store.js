import Vuex from "vuex";
import Vue from 'vue'

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        groupList: [],
        timetable: []
    },
    mutations: {
        setGroupList(state, groupList){
            state.groupList = groupList;
        },
        setTimeTable(state, timetable){
            state.timetable = timetable;
        },
        clearTimeTable(state){
            state.timetable = [];
        },
        sortTimeTable(state){
            state.timetable.sort(function (a, b) {
                return a.date.localeCompare(b.date);
            })
        },
        convertNoteToJson(state){
            state.timetable.forEach((item, index) => {
                if(item.note == null){
                    state.timetable[index].note = { "special" : "", "other" : ""};
                } else state.timetable[index].note = JSON.parse(item.note);
            })
        }
    },
});

export default store