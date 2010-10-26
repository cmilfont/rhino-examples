/*
 Exemplo de recursão 
*/
var cp = {
  name: "Christiano Milfont",
  type: {
    name: "Um cara em busca de riquezas"
  }
};
var el = {
  weight: 180
};
print(el.toSource());
(function merge(el, cp) {
    for(i in cp) {
        if( typeof cp[i] == 'object') {
            if(el[i] == undefined) {
                el[i] = cp[i]
            }
            merge(el[i], cp[i]);
        } else {
            el[i] = cp[i];
        }
    }
})(el, cp);
print(el.toSource());

/*
passagem de parametros
*/
var config = {
  name: "Christiano Milfont",
  weight: 180
};
var resposta = (function(config) {
	  var name = config.name || "default";
	  return name;
	})(config); 
print(resposta);