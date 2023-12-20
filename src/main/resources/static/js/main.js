let app = document.getElementById('typewriter');
 
let typewriter = new Typewriter(app, {
  loop: true,
  delay: 75,
});
 
typewriter
  .pauseFor(2500)
  .typeString('¡Ofreciendote los mejores productos y el mejor servicio!')
  .pauseFor(2000)
  .deleteChars(10)
  .start();