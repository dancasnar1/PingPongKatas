let partB = [];
let partI = [];
let partN = [];
let partG = [];
let partO = [];
let bingo = [];
let bingoNumbers = [];

function sleep(seconds) 
{
    const e = new Date().getTime() + (seconds * 1000);
    while (new Date().getTime() <= e) {}
}

const createFiveRandoms = (min, max) => {
    let array = [];
    for(let i = 0; i < 5; i++) {
        let random = Math.floor(Math.random()*(max - min) + min);
        if (!array.includes(random))
            array.push(random);
        else
            i--;
    }
    return array;
}

const generateNumbers = (array) => {
    let random = Math.floor(Math.random()*(75 - 1) + 1);
    while (array.includes(random))
        random = Math.floor(Math.random()*(75 - 1) + 1);
    array.push(random);
    array.sort((a, b) => a - b);
    return random;
}

partB = createFiveRandoms(1, 15);
partI = createFiveRandoms(16, 30);
partN = createFiveRandoms(31, 45);
partG = createFiveRandoms(46, 60);
partO = createFiveRandoms(61, 75);
bingo = partB.concat(partI, partN, partG, partO);

console.log(`Your card is:\n${bingo.join(' ')}`);
bingo.sort((a, b) => a - b);
console.log("The game starts...");
sleep(2);
console.log("NOW!!!");
sleep(1);

while (bingo.length > 0) {
    const random = generateNumbers(bingoNumbers);
    if (bingo.includes(random)) {
        const index = bingo.indexOf(random);
        bingo.splice(index, 1);
    }
    
    sleep(1);     // Optional
    console.log(bingoNumbers.join(" "));
}

console.log("\nBINGO!!!");
