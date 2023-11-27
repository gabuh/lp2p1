


const checkUserExists = async (type, identifier) => {
    try {
      const response = await fetch(`/register?${type}=${identifier}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      });
  
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
  
      const data = await response.json();
      return data.usernameExists;
    } catch (error) {
      console.error('Error during user check:', error);
      return false;
    }
  };


function checkFieldEvent (field){
    let tag
    let isRegister = false;
    let isLogin = false;

    if (field.classList.value.includes("options"))
        tag = "select"
    else
        tag = "input"

    if (field.classList.value.includes("login"))
        isLogin = true
    if (field.classList.value.includes("register"))
        isRegister = true

    field.getElementsByTagName(tag)[0].addEventListener("change",(event) => {
       const identifier = event.target.value
       const type = event.target.name
       const el = field.getElementsByClassName("help")[0]
        const elIcon = field.getElementsByClassName("is-right")[0]
       const childMsg = document.createElement("p")
       const childCheckIcon = document.createElement("span")
        childCheckIcon.classList.add('icon', 'is-right')

        if (elIcon != null){
            field.getElementsByClassName("control")[0].removeChild(elIcon)
            event.target.classList.remove("is-danger", "is-success")
        }
        if(el != null){
            field.removeChild(el)
        }

        const check = inputValidator.verify(type, identifier)

        console.log("check: " + check )

        if (typeof check === "string" || !check) {
            if(isRegister){
                registerForm.removeItem(type)
                registerForm.check()
            }else if (isLogin){
                loginForm.removeItem(type)
                loginForm.check()
            }
            if(tag === "input"){
                event.target.classList.add("is-danger")
                childMsg.innerHTML=typeof check === "string"?check:(type+" invalido") +"."
                childMsg.classList.add('help', 'is-danger')
                childCheckIcon.innerHTML = `<i class="ion-alert"></i>`
                field.appendChild(childMsg)
                field.getElementsByClassName("control")[0].appendChild(childCheckIcon)
            }
        } else {
            if (isRegister){
                registerForm.addItem(type)
                registerForm.check()
            }else if(isLogin){
                loginForm.addItem(type)
                loginForm.check()
            }

            if(tag === "input"){
                event.target.classList.add("is-success")
                childMsg.classList.add('help', 'is-success')
                childMsg.innerHTML = "Valid "+ type + "."
                childCheckIcon.innerHTML = `<i class="ion-checkmark"></i>`
                field.appendChild(childMsg)
                field.getElementsByClassName("control")[0].appendChild(childCheckIcon)
            }
        }

    })
}

for (const field of document.getElementsByClassName("field register") )
    checkFieldEvent(field)
for (const field of document.getElementsByClassName("field login"))
    checkFieldEvent(field)


const inputValidator = {

    verify(type, input){
        if (type === "email")
            return this.validateEmail(input)
        if (type === "username")
            return this.validateUsername(input)
        if (type === "senha")
            return this.validatePassword(input)
        if (type === "nacionalidade")
            return this.validateNationality(input)
        if (type === "dataNascimento")
            return this.validateBirthDate(input)
        if (type === "nome")
            return this.validateName(input)
        if (type === "usernameLogin")
            return this.genericValidation(input)
        if (type === "passwordLogin")
            return this.validateLoginPassword(input)
    },

    validateUsername(username){
        if (username === null || username === "")
            return "Username Invalido"

        if (username.length < 5 || username.length > 16)
            return "Username deve conter entre 5-16 characters"

        for (const char of username)
            if (!this.isDigitOrLetter(char) && char !== '_')
                return "Use letras numeros e underscores"

        if (!this.isLetter(username[0]))
            return "Username deve iniciar com uma letra"

        if (!this.isLetter(username[username.length -1 ]))
            return "Username deve terminar com uma letra"

        this.check(username).catch(() => {
            return "algo deu errado"
        })

        return "checking"
    },
    async check(username){
        try {
            let isRegister = false
            const checkUser = await checkUserExists("username", username)
            const control = document.getElementById("usernameControl")
            const field = document.getElementById("usernameField")
            const el = field.getElementsByClassName("help")[0]
            const elIcon = field.getElementsByClassName("is-right")[0]
            const input = field.getElementsByTagName('input')[0]

            if (elIcon != null){
                control.removeChild(elIcon)
                input.classList.remove("is-danger", "is-success")
            }
            if(el != null){
                field.removeChild(el)
            }

            if (field.classList.value.includes("register"))
                isRegister = true
            const childMsg = document.createElement("p")
            const childCheckIcon = document.createElement("span")
            childCheckIcon.classList.add('icon', 'is-right')
            if (checkUser) {
                if(isRegister){
                    registerForm.removeItem(input.name)
                    registerForm.check()
                }
                input.classList.add("is-danger")
                childMsg.classList.add('help', 'is-danger')
                childCheckIcon.innerHTML = `<i class="ion-alert"></i>`
                childMsg.innerHTML = "Este username não está disponível"
                field.appendChild(childMsg)
                control.appendChild(childCheckIcon)
            } else {
                if(isRegister){
                    registerForm.addItem(input.name)
                    registerForm.check()
                }
                input.classList.add("is-success")
                childMsg.classList.add('help', 'is-success')
                childCheckIcon.innerHTML = `<i class="ion-checkmark"></i>`
                childMsg.innerHTML = "Este username está disponível"
                field.appendChild(childMsg)
                control.appendChild(childCheckIcon)
            }

        } catch (error) {
            console.log(error)
        }
    }
    ,

    validateName(name){
        if (name === null || name === "")
            return false

        if (name.length < 5 || name.length > 50)
            return "Nome deve conter entre 5-50 characters"

        for (const char of name)
            if (!this.isLetter(char) && char !== ' ')
                return "sem numeros ou simbolos"

        return true

    },

    validateNationality(nationality){
        return !(nationality === '' || nationality === null);

    },

    validateBirthDate(birthDate){
        if (birthDate === null || birthDate === "")
            return false
        return true
    },

    validateEmail(email){
        if (email === null || email === "" )
            return false

        if (!this.isLetter(email[0]))
            return false

        const parts = email.split("@")
        if (parts.length !== 2)
            return false

        if (email.includes("@.") || email.includes(".@"))
            return false

        const localPart = parts[0]
        const domain = parts[1]

        if (localPart === "" || domain === "")
            return false

        let specialCharacters = "!#$%^&*()=+[]{}|;:'\",<>?/"
        for (const char of email)
            if (specialCharacters.includes(char))
                return false

        if (!domain.includes("."))
            return false


        specialCharacters = "-_"
        for (const char of domain)
            if (specialCharacters.includes(char) || !isNaN(char))
                return false


        if (domain.includes(".."))
            return false


        return true

    },

    validatePassword(password){
        if (password === null || password === "")
          return "Senha Invalida"

        if (password.length < 8)
            return "Deve conter entre 8-20 characters"

        if (!this.containsUppercase(password))
            return "Pelo ao menos 1 letra maiscula"

        if (!this.containsLowercase(password))
            return "Pelo ao menos 1 letra minuscula"

        if (!this.containsDigit(password))
            return "Pelo ao menos 1 numero"

        if (!this.containsSpecialCharacter(password))
            return "Pelo ao menos um character especial"


        if (document.getElementById("firstRgPassword").value !== document.getElementById("secondRgPassword").value )
            return "As senhas devem ser as mesmas"


        return true

    },

    validateLoginPassword(password){
        if (password === null || password === "")
            return "Senha Invalida"

        if (password.length < 8)
            return "Deve conter entre 8-20 characters"

        if (!this.containsUppercase(password))
            return "Pelo ao menos 1 letra maiscula"

        if (!this.containsLowercase(password))
            return "Pelo ao menos 1 letra minuscula"

        if (!this.containsDigit(password))
            return "Pelo ao menos 1 numero"

        if (!this.containsSpecialCharacter(password))
            return "Pelo ao menos um character especial"

        return true
    },

    genericValidation(input){
        if (input === null || input === ' ')
            return false;

        return true
    },

    isDigitOrLetter(char) {
        const charCode = char.charCodeAt(0);
        // Check if the character is a digit (0-9) or a letter (a-zA-Z)
        return (charCode >= 48 && charCode <= 57) ||       // Digit
            (charCode >= 65 && charCode <= 90) ||       // Uppercase letter
            (charCode >= 97 && charCode <= 122);        // Lowercase letter
    },
    isLetter(char) {
        const charCode = char.charCodeAt(0);
        // Check if the character is an uppercase or lowercase letter
        return (charCode >= 65 && charCode <= 90) ||  // Uppercase letter
            (charCode >= 97 && charCode <= 122);   // Lowercase letter
    },
    containsUppercase(input) {
        for (const char of input) {
            if (char >= 'A' && char <= 'Z') {
                return true;
            }
        }
        return false;
    },
    containsLowercase(input) {
        for (const char of input) {
            if (char >= 'a' && char <= 'z') {
                return true;
            }
        }
        return false;
    },
    containsDigit(input) {
        for (const char of input) {
            if (char >= '0' && char <= '9') {
                return true;
            }
        }
        return false;
    },
    containsSpecialCharacter(input) {
        const specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";
        for (const char of input) {
            if (specialCharacters.includes(char)) {
                return true;
            }
        }
        return false;
    },


}

const registerForm = {
    button : document.getElementById("submitRegister"),
    item : [],
    disableButton(){
        this.button.setAttribute("disabled", "disabled");
    },
    ableButton(){
        this.button.removeAttribute("disabled")
    },
    addItem(item){
        if (!this.item.includes(item))
            this.item.push(item)
    },
    removeItem(item){
        let index = this.item.indexOf(item)
        if (index > -1)
            this.item.splice(index,1)
    },
    check(){
        if (this.item.length === 6){
            this.ableButton()
        }else{
            this.disableButton()
        }
    },

}

const loginForm = {
    button : document.getElementById("submitLogin"),
    item : [],
    disableButton(){
        this.button.setAttribute("disabled", "disabled");
    },
    ableButton(){
        this.button.removeAttribute("disabled")
    },
    addItem(item){
        if (!this.item.includes(item))
            this.item.push(item)
    },
    removeItem(item){
        let index = this.item.indexOf(item)
        if (index > -1)
            this.item.splice(index,1)
    },
    check(){
        if (this.item.length === 2){
            this.ableButton()
        }else{
            this.disableButton()
        }
    },

}