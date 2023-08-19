const lengthRange = document.getElementById("passwordLengthRange");
const lengthNumber = document.getElementById("passwordLengthNumber");
const form = document.getElementById("passwordGeneratorForm");

const passwordLength = lengthNumber.value

const passwordElement = document.getElementById("passwordDisplay")
const valueElement = document.getElementById("value");
const keyElement = document.getElementById("key")
const includeUppercaseElement = document.getElementById("includeUppercase");
const includeSymbolsElement = document.getElementById("includeSymbols");

lengthNumber.addEventListener('input', syncAmount);

lengthRange.addEventListener('input', syncAmount);
form.addEventListener('submit', e => {
    e.preventDefault();
    const value = valueElement.value;
    const key = keyElement.value;
    const includeUppercase = includeUppercaseElement.checked
    const includeSymbols = includeSymbolsElement.checked
    passwordElement.innerText = generatePassword(value, key, passwordLength, includeUppercase, includeSymbols);
})

function generatePassword(value, key, passwordLength, includeUppercase, includeSymbols) {
    const xhr = new XMLHttpRequest();
    const urlTemplate = "http://localhost:8080/password/forValue?"
        + "value=" + value
        + "&key=" + key
        + "&length=" + passwordLength
        + "&u=" + includeUppercase
        + "&s=" + includeSymbols
    xhr.open("GET", urlTemplate);
    xhr.send();

    console.log(xhr.responseText)
    return xhr.responseText;
}

function syncAmount(event) {
    const value = event.target.value;
    lengthRange.value = value;
    lengthNumber.value = value;
}