
export default class ThrowableExceptions {

     BadCredentialsException = "Неверный email или пароль!";
     UserWithEmailIsExistException = 'Пользователь с данным email уже существует!';
     PasswordConfirmationException = 'Поля пароль и подтверждение пароля не совпадают!';

     static getMessage(e){
          let ex = new ThrowableExceptions();
          console.log(e);
          for (let i in ex){
               console.log(e.toString().includes(i.toString()) + ' '+ i +  ` ${ex[i]}`);
               if(e.toString().includes( i.toString())){
                    return  ex[i];
               }
          }

          return 'no available message';
     }
}
