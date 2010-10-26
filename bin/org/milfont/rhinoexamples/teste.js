// java -jar js.jar -version 170 teste.js
 
function range(begin, end) {
  for (let i = begin; i < end; ++i) {
    yield i;
  }
}

var ten_squares = [i * i for each (i in range(0, 10))];
print(ten_squares);
