const MAX_PARTICLES = 50000,
    NFIELDS = 5, // x, y, vx, vy, age,
    // size of the array
    PARTICLES_LENGTH = MAX_PARTICLES * NFIELDS;

window.starFly = starFly

function starFly(fromX, fromY, toX, toY, durationSecond, onComplete) {
    const canvas = document.createElement("canvas")
    canvas.style.zIndex = 200
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    canvas.style.position = "absolute"
    document.body.appendChild(canvas)

    const star = document.createElement("div")
    star.id = "star"
    star.style.zIndex = 201
    star.style.top = "0px"
    star.style.left = "0px"
    star.style.backgroundColor = "transparent"
    star.style.position = "absolute"
    star.appendChild(document.createTextNode("⭐"))
    document.body.appendChild(star)

    const context = {
        canvas: canvas,
        ctx: canvas.getContext("2d"),
        x: fromX,
        y: fromY,
        particles_i: 0,
        particles: new Float32Array(PARTICLES_LENGTH),
        t0: new Date() * 1
    }

    // let tl = gsap.timeline({
    //     onComplete: function () {
    //         context.x = null
    //         context.y = null
    //         context.completed = true
    //         onComplete()
    //     }
    // });

    gsap.fromTo("#star", {
        x: fromX,
        y: fromY
    }, {
        duration: durationSecond,
        x: toX,
        y: toY,
        // ease: "elastic.out(1, 0.3)",
        ease: "sine",
        onUpdate: function () {
            let elem = this.targets()[0];
            context.x = gsap.getProperty(elem, "x") + 10
            context.y = gsap.getProperty(elem, "y") + 10
        },
        onComplete: function () {
            context.x = null
            context.y = null
            onComplete()
        }
    })

    let timeline = gsap.timeline()
    timeline.to("#star", {
        duration: durationSecond / 2,
        scale: 2
    })
    timeline.to("#star", {
        duration: durationSecond / 2,
        scale: 1
    })

    gsap.fromTo("#star",
        {rotation: 0},
        {rotation: 360, duration: durationSecond / 2, repeat: 1});

    draw(context)
}

function draw(context) {
    let t1 = new Date() * 1,
        // time delta in seconds
        td = (t1 - context.t0) / 1000,
        MAX_AGE = 5,
        width = context.canvas.width,
        height = context.canvas.height,
        gravity = 50,
        drag = 0.999,
        // color
        r = 255,
        g = 215,
        b = 0;
    context.t0 = t1;

    // emit particles
    emit(context);

    // context.ctx.clearRect(0, 0, width, height);
    const imgdata = context.ctx.createImageData(width, height),
            // context.ctx.getImageData(0, 0, width, height),
        data = imgdata.data;

    let particlesToDraw = 0
    for (let i = 0; i < PARTICLES_LENGTH; i += NFIELDS) {
        // check age
        if ((context.particles[i + 4] += td) > MAX_AGE) continue;
        // ~~ = double bitwise inversion = Math.ceil
        let x = ~~(context.particles[i] = (context.particles[i] + (context.particles[i + 2] *= drag) * td)),
            y = ~~(context.particles[i + 1] = (context.particles[i + 1] + (context.particles[i + 3] = (context.particles[i + 3] + gravity * td) * drag) * td));

        // check bounds
        if (checkBounds(x, y, context.canvas.width, context.canvas.height)) continue;

        // calculate offset
        const offset = (x + y * context.canvas.width) * 4;

        particlesToDraw++
        // set pixel
        data[offset] = r;
        data[offset + 1] = g;
        data[offset + 2] = b;
        data[offset + 3] = 255;
    }

    context.ctx.putImageData(imgdata, 0, 0);
    if (particlesToDraw !== 0) {
        requestAnimationFrame(function () {
            draw(context)
        });
    }
}

// random numbers in the form of range +/- base
function fuzzy(range, base) {
    return (base || 0) + (Math.random() - 0.5) * range * 2;
}


// Don't render particles which are not on canvas
function checkBounds(x, y, width, height) {
    return x < 0 || x >= width || y < 0 || y >= height;
}

function emit(context) {
    if (context.x == null || context.y == null) {
        return
    }
    for (let i = 0; i < 250; i++) {
        context.particles_i = (context.particles_i + NFIELDS) % PARTICLES_LENGTH;
        context.particles[context.particles_i] = context.x;
        context.particles[context.particles_i + 1] = context.y;
        const alpha = fuzzy(Math.PI),
            radius = Math.random() * 100,
            vx = Math.cos(alpha) * radius,
            vy = Math.sin(alpha) * radius,
            age = Math.random();
        context.particles[context.particles_i + 2] = vx;
        context.particles[context.particles_i + 3] = vy;
        context.particles[context.particles_i + 4] = age;
    }
}
