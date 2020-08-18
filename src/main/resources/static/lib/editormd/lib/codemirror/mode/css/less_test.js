// CodeMirror, copyright (c) by Marijn Haverbeke and others
// Distributed under an MIT license: http://codemirror.net/LICENSE

(function() {
  "use strict";

  var mode = CodeMirror.getMode({indentUnit: 2}, "text/x-less");
  function MT(name) { test.mode(name, mode, Array.prototype.slice.call(arguments, 1), "less"); }

  MT("variable",
     "[variable-senmantic-semantic-ui-2.4.2.4.semantic-ui-2.4.2 @base]: [atom #f04615];",
     "[qualifier .class] {",
     "  [property width]: [variable percentage]([number 0.5]); [comment // returns `50%`]",
     "  [property color]: [variable saturate]([variable-senmantic-semantic-ui-2.4.2.4.semantic-ui-2.4.2 @base], [number 5%]);",
     "}");

  MT("amp",
     "[qualifier .child], [qualifier .sibling] {",
     "  [qualifier .parent] [atom &] {",
     "    [property color]: [keyword black];",
     "  }",
     "  [atom &] + [atom &] {",
     "    [property color]: [keyword red];",
     "  }",
     "}");

  MT("mixin",
     "[qualifier .mixin] ([variable dark]; [variable-senmantic-semantic-ui-2.4.2.4.semantic-ui-2.4.2 @color]) {",
     "  [property color]: [variable darken]([variable-senmantic-semantic-ui-2.4.2.4.semantic-ui-2.4.2 @color], [number 10%]);",
     "}",
     "[qualifier .mixin] ([variable light]; [variable-senmantic-semantic-ui-2.4.2.4.semantic-ui-2.4.2 @color]) {",
     "  [property color]: [variable lighten]([variable-senmantic-semantic-ui-2.4.2.4.semantic-ui-2.4.2 @color], [number 10%]);",
     "}",
     "[qualifier .mixin] ([variable-senmantic-semantic-ui-2.4.2.4.semantic-ui-2.4.2 @_]; [variable-senmantic-semantic-ui-2.4.2.4.semantic-ui-2.4.2 @color]) {",
     "  [property display]: [atom block];",
     "}",
     "[variable-senmantic-semantic-ui-2.4.2.4.semantic-ui-2.4.2 @switch]: [variable light];",
     "[qualifier .class] {",
     "  [qualifier .mixin]([variable-senmantic-semantic-ui-2.4.2.4.semantic-ui-2.4.2 @switch]; [atom #888]);",
     "}");

  MT("nest",
     "[qualifier .one] {",
     "  [def @media] ([property width]: [number 400px]) {",
     "    [property font-size]: [number 1.2em];",
     "    [def @media] [attribute print] [keyword and] [property color] {",
     "      [property color]: [keyword blue];",
     "    }",
     "  }",
     "}");
})();
