
let timerInterval;


function startTimer(duration) {
    let time = duration;
    let timerDisplay = document.getElementById("timer");

    if (!timerDisplay) return;

    timerInterval = setInterval(function () {

        let minutes = Math.floor(time / 60);
        let seconds = time % 60;

        if (seconds < 10) {
            seconds = "0" + seconds;
        }

        timerDisplay.innerText = "Time Left: " + minutes + ":" + seconds;

        if (time <= 60) {
            timerDisplay.style.color = "red";
        }

        if (time <= 0) {
            clearInterval(timerInterval);

            let form = document.getElementById("quizForm");
            if (form) {
                form.submit();
            }

            return;
        }

        time--;

    }, 1000);
}
// Start Exam
function startExam() {
    let subject = document.getElementById("subject").value;
    let agreeElement = document.getElementById("agree");

    if (!subject) {
        Swal.fire({
            icon: "warning",
            title: "Oops...",
            text: "Please select a subject!"
        });
        return;
    }

    if (agreeElement && !agreeElement.checked) {
        Swal.fire({
            icon: "warning",
            title: "Attention",
            text: "Please agree to the instructions!"
        });
        return;
    }

    Swal.fire({
        title: "Start Exam?",
        text: "Once started, you cannot go back!",
        icon: "question",
        showCancelButton: true,
        confirmButtonText: "Yes, Start",
        cancelButtonText: "Cancel"
    }).then((result) => {
        if (result.isConfirmed) {

            localStorage.setItem("subject", subject);

            Swal.fire({
                title: "Good Luck!",
                text: "Your exam is starting...",
                icon: "success",
                timer: 1500,
                showConfirmButton: false
            }).then(() => {

                // create form dynamically
                let form = document.createElement("form");
                form.method = "POST";
                form.action = "ExamServlet";

                let input = document.createElement("input");
                input.type = "hidden";
                input.name = "subject";
                input.value = subject;

                form.appendChild(input);
                document.body.appendChild(form);
                form.submit();
            });
        }
    });
}


// When exam page loads
window.onload = function () {
    let subject = localStorage.getItem("subject");
    let title = document.getElementById("subjectTitle");

    if (title && subject) {
        title.innerText = "Subject: " + subject.toUpperCase();

        loadQuestions();
        startTimer(questions.length * 3);
    }
};

// Timer

// Submit Exam
function submitExam() {
    clearInterval(timerInterval);

    let score = 0;
    let total = questions.length;

    questions.forEach((q, index) => {
        let selected = document.querySelector(`input[name="q${index}"]:checked`);

        if (selected && parseInt(selected.value) === q.answer) {
            score++;
        }
    });

    localStorage.setItem("score", score);
    localStorage.setItem("total", total);

    window.location.href = "result.html";
}

// Shuffle options
function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        let j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}

// Load Questions
