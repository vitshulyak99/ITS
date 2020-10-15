<template>
    <div class="p-1">
        <ul class="list-group">
            <li v-for="report in reports" :key="report.id" class="list-group-item border">
                <div class="d-flex flex-column">
                    <div class="flex-row">
                        <label for="report-title">Тема: </label>
                        <span id="report-title" >{{report.title}}</span>
                    </div>
                    <div class="flex-row">
                        <label for="report-status">Статус: </label>
                        <span id="report-status">{{report.status}}</span>
                    </div>
                    <div class="font_10">
                        <ul class="list-group">
                            <li v-for="message in report.reviewerMessages" :key="message.id" class="list-group-item">
                                <div class="d-flex flex-column">
                                    <div class="flex-column">
                                        <label for="reviewer">Рецензент:</label>
                                        <span id="reviewer" >{{message.name}}</span>
                                    </div>

                                    <div class="flex-column">
                                        <label for="reviewer-message">Отзыв:</label>
                                        <span class="ml-1" id="reviewer-message" >
                                            {{ message.message}}
                                        </span>
                                    </div>

                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: "ReportList",
        data(){
           return {
               reports:[
                   {
                       id:'0',
                       title:'Решение задачи классификации с помощью сверточных нейронных сетей',
                       status:'отклонен',
                       reviewerMessages:[
                           {
                               id:'0',
                               name:'Иван Иванович Иванов',
                               message:'Отклонен по причине...'
                           }
                       ]
                   }
               ]
           }
        }
    }
</script>

<style scoped>
.font_10{
    font-size: 10px;
}
    span{
        margin-left: 2px;
    }
</style>