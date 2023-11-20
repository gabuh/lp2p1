document.addEventListener('DOMContentLoaded', () => {
    const navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);
    if (navbarBurgers.length > 0) {
        navbarBurgers.forEach( el => {
            el.addEventListener('click', () => {
                const target = el.dataset.target;
                const $target = document.getElementById(target);
                el.classList.toggle('is-active');
                $target.classList.toggle('is-active');
            });
        });
    }
});


document.addEventListener('DOMContentLoaded', () => {
    // Functions to open and close a modal
    function openModal($el) {
        $el.classList.add('is-active');
    }

    function closeModal($el) {
        $el.classList.remove('is-active');
    }

    function closeAllModals() {
        (document.querySelectorAll('.modal') || []).forEach(($modal) => {
            closeModal($modal);
        });
    }

(document.querySelectorAll('.js-modal-trigger') || []).forEach(($trigger) => {
    const modal = $trigger.dataset.target;
    const $target = document.getElementById(modal);

    $trigger.addEventListener('click', () => {
        openModal($target);
    });
});

// Add a click event on various child elements to close the parent modal
(document.querySelectorAll('.modal-background, .modal-close, .modal-card-head .delete, .modal-card-foot .button') || []).forEach(($close) => {
    const $target = $close.closest('.modal');

    $close.addEventListener('click', () => {
        closeModal($target);
    });
});

// Add a keyboard event to close all modals
document.addEventListener('keydown', (event) => {
    if (event.code === 'Escape') {
        closeAllModals();
    }
});
});




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
    let tag;
    if (field.classList.value.includes("options"))
        tag = "select"
    else
        tag = "input"

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
            registerForm.removeItem(type)
            registerForm.check()
            if(tag === "input"){
                event.target.classList.add("is-danger")
                childMsg.innerHTML=typeof check === "string"?check:(type+" invalido") +"."
                childMsg.classList.add('help', 'is-danger')
                childCheckIcon.innerHTML = `<i class="ion-alert"></i>`
                field.appendChild(childMsg)
                field.getElementsByClassName("control")[0].appendChild(childCheckIcon)
            }
        } else {
            registerForm.addItem(type)
            registerForm.check()
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


        checkUserExists(username)
        .then((exists) => {
            if (exists)
                return "This username is not available"
        })
        .catch((error) => {
            console.log(error)
        })

        return true
    },

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

