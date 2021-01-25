# anotações

Clojure executa forms, código que converge para um valor, um código que funciona.

A forma de uma operação é dada por:

(operador operando1 operando2 etc)

Não segue a notação infixa de operadores que nem js por exemplo:

1 + 2 + 3

Clojure possui operadores para controle de fluxo: if, do e when.

if:

(if boolean-form then-form optional-else-form)

exemplo:

(if true 1 0) => 1
(if false 1 0) => 0
(if true 1) => 1
(if false 1) => nil = null

do:

operador para uma branch de condicional executar um form:

(if true (do form1 form2 form3))

when:

é um if com do sem else.

(when true form1 form2)
false is nil

verificando veracidade:

o operador ? como sufixo representa uma pergunta para o operando:

"O operando é do tipo do operador?"

e.g.:

(nil? 1) => false
(nil? nil) => false
(int? 1) => true

Igualdade:

O operador de igualdade retorna verdadeiro ou falso:

(= 1 1) => true
(= 1 2) => false

And e Or:

and retorna o último operando caso todos sejam verdadeiros e retorna nil caso contrário
or retorna o primeiro verdadeiro que encontrar e retorna o último caso contrário

(or false 1 2 3) => 3
(or false false) => false

(and 1 2 3) => 3
(and 1 false 3) => nil

Atribuição

(def name value)

name agora terá o valor de value para sempre, um const.

Vector e List e Sets e Maps

[ 1, 2 ,3 ] vector, conj adiciona no fim

( 1, 2, 3 ) list, conj adiciona no começo

Sets são conjuntos.

#{1 2 3} / hash-set / set

contains?

forms são avaliados recursivamente sobre os operandos.

Definindo funçõa:

(defn nome "documentation" [param1 param2 param3] (corpo-da-func))

é possível ler a doc de uma func com

(doc fn-name)

multiple-arity functions:

(defn fn-name
    ([arg1 arg2 arg3]
        (fn-body1)
        )
    ([arg1 arg2]
        (fn-body2)
        )
    ([arg1]
        (fn-body3)
        )
    )

é uma estratégia de executar a função com valores default.

variable-arity, é para ter um número indefinido de argumentos:

(defn fn-name [&args] fn-body)

é possível misturar:

(defn fn-name [arg1 & arg2] fn-body)

desestruturação:

(defn fn-name [[arg1 arg2 & argN]] fn-body)

a função retorna como valor o último forms evaluado.

função anônima

(fn [param-list] fn-body)

usa-se % para argumento. %1 é equivalente a %. Pode-se discriminar com
%1, %2, %3, etc.

(#(str %1 " e " %2) "mari" "kenzo") => mari e kenzo

closure

função que é retornada por outra função. ela possui os argumentos que estão
no escopo da função criada também.