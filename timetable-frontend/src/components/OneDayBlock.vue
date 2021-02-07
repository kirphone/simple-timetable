<template>
    <div class="one-day-container">

        <div class="date">
            <p>{{new Date(info[0].date).toLocaleString("ru", {month:"short", day:"numeric"})}}</p>
            <p>{{new Date(info[0].date).toLocaleString("ru", {weekday:"short"})}}</p>
        </div>
        <div :class="isFirst ? 'one-day-desktop-container first-container' : 'one-day-desktop-container'">
            <div v-for="(oneLesson, index) in info" :key="index" class="one-lesson">
                <div class="block-time">
                    <p v-if="oneLesson.startTime != null">{{oneLesson.startTime.substring(0, 5)}}</p>
                    <p v-if="oneLesson.startTime != null">{{oneLesson.endTime.substring(0, 5)}}</p>
                </div>
                <div class="block-subject">
                    <p>{{oneLesson.subjectName}}</p>
                    <p>{{typeOfLesson[oneLesson.type]}}</p>
                    <p>{{oneLesson.note.other}}</p>
                    <p>{{oneLesson.note.special}}</p>
                </div>
                <div class="block-teacher">
                    {{oneLesson.teacherName}}
                </div>
                <div class="block-address">
                    {{oneLesson.classroom}}
                </div>
            </div>
        </div>

        <div :class="isFirst ? 'one-day-mobile-container first-container' : 'one-day-mobile-container'">
            <div v-for="(oneLesson, index) in info" :key="index" class="one-lesson">
                <div class="block-time-address">
                    <p v-if="oneLesson.startTime != null">{{oneLesson.startTime.substring(0, 5)}}
                    <p v-if="oneLesson.startTime != null">{{oneLesson.endTime.substring(0, 5)}}</p>
                    <p>{{oneLesson.classroom}}</p>
                </div>
                <div class="block-subject-teacher">
                    <p>{{oneLesson.subjectName}}</p>
                    <p>{{oneLesson.teacherName}}</p>
                    <p>{{oneLesson.note.other}}</p>
                    <p>{{oneLesson.note.special}}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "OneDayBlock",
        props: {
            info: [],
            isFirst : Boolean
        },
        data() {
            return {
                typeOfLesson: {"Л" : "Лабораторная работа", "С" : "Семинар", "СРС" : "CРС", "C" : "Семинар"}
            }
        }
    }
</script>

<style scoped>
    .one-day-container {
        display: flex;
        align-items: center;
        font-size: 18px;
        line-height: 21px;
    }

    .one-day-desktop-container, .one-day-mobile-container {
        box-sizing: border-box;
        border-radius: 34px;
        margin-left: 15px;
        background: white;
        flex-direction: column;
        box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.5);
    }

    .first-container{
        border: 10px solid rgba(111, 181, 42, 0.5);
    }

    .one-day-desktop-container {
        display: flex;
    }

    .one-day-mobile-container {
        display: none;
    }

    .date {
        font-family: Roboto;
        font-style: normal;
        font-weight: normal;
    }

    .one-lesson {
        display: flex;
        padding-top: 1.2vh;
        padding-bottom: 0.675vh;
        align-items: center;
        width: 73vw;
        height: 15vh;
    }

    .one-lesson > div {
        text-align: center;
        display: flex;
        align-items: center;
        justify-content: center;
        height: calc(100% - 1.875vh);
    }

    .one-lesson > div:not(:last-child) {
        border-right: 3px solid rgba(0, 0, 0, 0.24);
    }

    .block-time, .block-time-address {
        grid-area: time;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        flex-basis: 20%;
    }

    .block-subject {
        flex-basis: 40%;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }

    .block-teacher {
        flex-basis: 30%;
    }


    .block-address {
        flex-basis: 10%;
    }

    .block-time-address{
        flex-basis: 30%;
    }

    .block-subject-teacher {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-items: center;
        flex-basis: 70%;
    }

    @media (max-width: 800px) {
        .one-day-desktop-container {
            display: none;
        }

        .one-day-mobile-container {
            display: flex;
        }

        .first-container{
            border: 10px solid rgba(111, 181, 42, 0.5);
        }

        .one-day-desktop-container, .one-day-mobile-container{
            margin-left: 5px;
        }
    }

    @media (max-width: 500px) {
        .one-day-container{
            font-size: 14px;
            line-height: 16px;
        }
    }

    @media (max-width: 380px) {
        .one-day-container{
            font-size: 10px;
            line-height: 12px;
        }
        .first-container{
            border-width: 5px;
        }
    }

    @media (max-width: 280px) {
        .one-day-container{
            font-size: 8px;
            line-height: 10px;
        }
    }
</style>