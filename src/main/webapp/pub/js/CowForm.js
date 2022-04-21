// const button = document.getElementById("button");
const $button = $("button[id*='button']");

const validateAnimalId1 = () => {
    //Define variables
    let error = document.getElementById("errorAId1")
    const aId1 = document.getElementById("animalId1").value;
    console.log(aId1);
    if(aId1.trim() == 0){
        error.textContent = "Cannot be blank";
        error.style.color = "red";
        $button.prop('disabled', true);
    }
    else{
        error.textContent = "";
        $button.prop('disabled', false);
        return true;
    }
}

const validateBreed = () =>{
    //Define variables
    // let re = new RegExp("[^\\s*$]");
    let error = document.getElementById("errorBreed");
    let lName = document.getElementById("breed").value;
    // let result = re.test(breed);

    if(lName.trim() == 0){
        error.textContent = "Cannot be blank";
        error.style.color = "red";
        $button.prop('disabled', true);
    }
    else{
        error.textContent = "";
        $button.prop('disabled', false);
        return true;
    }


}

const validateAnimalType = () => {
    //Define variables
    let error = document.getElementById("errorAType");
    if ($('input[name=animalType]:checked').length = 0) {
        // do something here
        error.textContent = "Cannot be blank";
        error.style.color = "red";
        $button.prop('disabled', true);
    }
}

const lastCheck = () => {
    let check1 = validateAnimalId1();
    let check2 = validateBreed();

    if(check1 == true && check2 == true){
        $button.prop('disabled', false);
    }
    else{
        $button.prop('disabled', true);
    }


}


