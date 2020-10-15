export default function passwordSymbolsValidator (value) {
    return /[a-zA-Z]+[0-9]+[!?_]+/.test(value);
}