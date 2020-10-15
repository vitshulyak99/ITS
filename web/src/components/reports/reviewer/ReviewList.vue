<template>
    <div class="d-flex flex-column ">
        <span class="text-center m-3"> Доклады прошедшие рецензию</span>
        <div id="list" class="p-2">

            <div v-for="report in reports" :key="report.id"
                 class="d-flex p-2 flex-column border border-info border-radius-15">
                <span>Загрузил: <i>{{report.reporter}}</i></span>
                <span>Тема: {{report.name}}</span>
                <span>Секция: {{report.section}}</span>
                <span>Статус: {{report.status}}</span>

                <div class="flex-column">
                    <label for="reviewer-message">Отзыв:</label>
                    <span class="ml-1" id="reviewer-message" >
                                            {{ report.reviewMessage}}
                                        </span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ReviewList",
        data(){
            return {
                reports:[
                    {
                        id:0,
                        reporter:'Иван Иванович Иванов',
                        name:'Решение задачи классификации',
                        status:'Одобрен',
                        reviewMessage:'Доклад одобрен!',
                        section:'Системы управления'
                    }
                ]
            }
        }
    }
</script>

<style scoped>

</style>