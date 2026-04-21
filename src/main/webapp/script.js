const counters = document.querySelectorAll(".no");

   counters.forEach(counter => {
       let target = +counter.getAttribute("data-target");
       let count = 0;

       let updateCount = () => {
           if(count < target){
               count++;
               counter.innerText = count + "%";
               setTimeout(updateCount, 20);
           } else {
               counter.innerText = target + "%";
           }
       }

       updateCount();
   });