const lengthRange = document.getElementById("passwordLengthRange");
const lengthNumber = document.getElementById("passwordLengthNumber");
const form = document.getElementById("passwordGeneratorForm");



const passwordElement = document.getElementById("passwordDisplay")
const valueElement = document.getElementById("value");
const includeUppercaseElement = document.getElementById("includeUppercase");
const includeSymbolsElement = document.getElementById("includeSymbols");

lengthNumber.addEventListener('input', syncAmount);

lengthRange.addEventListener('input', syncAmount);
form.addEventListener('submit', e => {
    e.preventDefault();
    const value = valueElement.value;
    const passwordLength = lengthNumber.value
    const includeUppercase = includeUppercaseElement.checked
    const includeSymbols = includeSymbolsElement.checked
    const passwordPromise = generatePassword(value, passwordLength, includeUppercase, includeSymbols);
    passwordPromise.then(text => passwordElement.innerText = text)
})

async function generatePassword(value, passwordLength, includeUppercase, includeSymbols) {
    const urlTemplate = "https://localhost:8443/password/forValue?"
        + "value=" + value
        + "&length=" + passwordLength
        + "&u=" + includeUppercase
        + "&s=" + includeSymbols

    const response = await fetch(urlTemplate);

    return response.text();
}

function syncAmount(event) {
    const value = event.target.value;
    lengthRange.value = value;
    lengthNumber.value = value;
}

function copyToClickBoard(){
    var content = document.getElementById("passwordDisplay").innerHTML;
    navigator.clipboard.writeText(content)
        .then(() => {
            console.log("Text has been copied!")
        })
        .catch(err => {
            console.log("Something is not right, Oops!");
        })
}