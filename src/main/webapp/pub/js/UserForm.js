const submit = document.getElementById("submit");

const validateFirstName = () => {
    //Define variables
    let regNums = new RegExp("[0-9]");
    let regSpace = new RegExp("[^\\s*$]");
    let error = document.getElementById("errorFname")
    const fName = document.getElementById("firstName").value;
    let resultNum = regNums.test(fName);
    let resultSpace = regSpace.test(fName);
    if(fName.trim() == 0){
        error.textContent = "Cannot be blank";
        error.style.color = "red";
    }
    else{
        error.textContent = "";
        if(resultNum == true && resultSpace == true){
            error.textContent = "Name cannot have space or numbers";
            error.style.color = "red";
        }
        else {
            error.textContent = ""
            return true;
        }
    }
}

const validateLastName = () =>{
    //Define variables
    let re = new RegExp("[0-9, ^\\s*$]");
    let error = document.getElementById("errorLname");
    let lName = document.getElementById("lastName").value;
    let result = re.test(lName);

    if(lName.trim() == 0){
        error.textContent = "Cannot be blank";
        error.style.color = "red";
    }
    else{
        error.textContent = "";
        if (result == true) {
            error.textContent = "Name cannot have space or numbers";
            error.style.color = "red";
        } else {
            error.textContent = "";
            return true;
        }
    }
}

const validateEmail = () =>{
    //Define variables
    let re = new RegExp("@.");
    let error = document.getElementById("errorEmail");
    let email = document.getElementById("email").value;
    let result = re.test(email);

    if(email.trim() == 0){
        error.textContent = "Cannot be blank";
        error.style.color = "red";
    }
    else{
        error.textContent = "";
        if (result == false){
            error.textContent = "Please enter a valid Email";
            error.style.color = "red";
        } else {
            error.textContent = ""
            return true;
        }
    }

}

const validatePassword = () =>{
    //Define variables
    const password = document.getElementById('password').value;
    let error = document.getElementById("errorPassword");
    const regCheck1 = new RegExp("[a-zA-Z]+");
    const regCheck2 = new RegExp("[0-9]+");

    if(password.trim() == 0){
        error.textContent = "Cannot be blank";
        error.style.color = "red";
    }
    else{
        error.textContent = "";
        //checking the length of the password
        if(password.length >= 8 && password.length <= 20){
            let resultCheck1 = regCheck1.test(password);
            let resultCheck2 = regCheck2.test(password);

            if(resultCheck1 == true && resultCheck2 == true){
                error.textContent = "";
                return true;
            }
            else{
                error.textContent = "Needs have letters and numbers in password";
                error.style.color = "red";
            }
        }
        else{
            error.textContent = "Password to short or to long - between 8 to 20 characters long";
            error.style.color = "red";
        }
    }


}

//TODO: This needs some help, ask Steven
const validateConfirmPassword = () =>{
    let pass1 = document.getElementById('password').value;
    let pass2 = document.getElementById('passwordConfirm').value;
    let error = document.getElementById("errorConfirmPassword");

    if(pass2.trim() == 0){
        error.textContent = "Cannot be blank";
        error.style.color = "red";
    }
    else{
        if (pass1 == pass2) {
            error.textContent = ""
            return true;
        } else {
            error.textContent = "Passwords need to match"
            error.style.color = "red"
}
    }
}

const mainValidateFunction = (e) => {
    e.preventDefault();
    let fNameCheck = validateFirstName();
    let lNameCheck = validateLastName();
    let emailCheck = validateEmail();
    let passwordCheck = validatePassword();
    let confirmPasswordCheck = validateConfirmPassword();
    if(
        fNameCheck == true &&
        lNameCheck == true &&
        emailCheck == true &&
        passwordCheck == true &&
        confirmPasswordCheck == true
    )
    {
        console.log("Passed");
        window.location.href = 'web.html';
    }
    else{
        alert("Failed to submit, please enter correct information");
        window.location.href = 'register.html';
    }
}

submit.addEventListener("click",mainValidateFunction);

