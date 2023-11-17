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
    field.getElementsByTagName("input")[0].addEventListener("change",(event) => {
       const identifier = event.target.value
       const type = event.target.name
       const el = field.getElementsByClassName("help")[0]
        const elIcon = field.getElementsByClassName("is-right")[0]
       const childMsg = document.createElement("p")
       const childCheckIcon = document.createElement("span")
        childCheckIcon.classList.add('icon', 'is-right')

        if (elIcon != null){
            field.getElementsByClassName("control")[0].removeChild(elIcon)
        }
        if(el != null){
            field.removeChild(el)
        }
       console.log( type + identifier)
       checkUserExists(type, identifier)
        .then((exists) => {
          if (exists) {
              event.target.classList.add("is-danger")
              childMsg.innerHTML= "This username is not available"
              childMsg.classList.add('help', 'is-danger')
              childCheckIcon.innerHTML = `<i class="ion-alert"></i>`
              field.appendChild(childMsg)
              field.getElementsByClassName("control")[0].appendChild(childCheckIcon)
          } else {
              event.target.classList.add("is-success")
              childMsg.innerHTML= "This username is available"
              childMsg.classList.add('help', 'is-success')
              childCheckIcon.innerHTML = `<i class="ion-checkmark"></i>`
              field.appendChild(childMsg)
              field.getElementsByClassName("control")[0].appendChild(childCheckIcon)
          }
        })
        .catch((error) => {
            console.log(error)
        });
    })
}

const usernameField = document.getElementById("usernameField");
checkFieldEvent(usernameField)


