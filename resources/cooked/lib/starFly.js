const MAX_PARTICLES = 2000,
    NFIELDS = 5, // x, y, vx, vy, age,
    // size of the array
    PARTICLES_LENGTH = MAX_PARTICLES * NFIELDS;

window.starFly = starFly

/**
 * @param canvas The canvas to display the animation. Size and zIndex must be set before calling this method.
 * @param starDiv The div containing star. Size and zIndex must be set before calling this method.
 * @param fromX
 * @param fromY
 * @param toX
 * @param toY
 * @param durationSecond
 * @param onStarFlyComplete star fly completes, but particles are still dropping
 * @param onComplete everything is done
 */
function starFly(canvas, starDiv, fromX, fromY, toX, toY, durationSecond, onStarFlyComplete, onComplete) {
    const context = {
        canvas: canvas,
        ctx: canvas.getContext("2d"),
        x: fromX,
        y: fromY,
        particles_i: 0,
        particles: new Float32Array(PARTICLES_LENGTH),
        t0: new Date() * 1
    }

    gsap.fromTo("#starfly-star", {
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
            // Wait particles to drop down
            document.getElementById("starfly-star").style.display = "none"
            onStarFlyComplete()
            window.setTimeout(onComplete, 3000)
        }
    })

    let timeline = gsap.timeline()
    timeline.to("#starfly-star", {
        duration: durationSecond / 2,
        scale: 3
    })
    timeline.to("#starfly-star", {
        duration: durationSecond / 2,
        scale: 1
    })

    gsap.fromTo("#starfly-star",
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

    const imgdata = context.ctx.createImageData(width, height),
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
        particlesToDraw++
        drawOnCanvas(context.canvas, data, x, y, r, g, b)
    }

    context.ctx.putImageData(imgdata, 0, 0);
    if (particlesToDraw !== 0) {
        requestAnimationFrame(function () {
            draw(context)
        });
    }
}

function drawOnCanvas(canvas, data, x, y, r, g, b) {
    // 50% 9x9 particle, 50% 1x1 particle
    let small = Math.random() > 0.5
    for (let i = -1; i <= 1; i++) {
        for (let j = -1; j <= 1; j++) {
            if (!small || (i === 0 && j === 0)) {
                // calculate offset
                const offset = (x + i + (y + j) * canvas.width) * 4;
                // set pixel
                data[offset] = r;
                data[offset + 1] = g;
                data[offset + 2] = b;
                data[offset + 3] = 255;
            }
        }
    }
}

// random numbers in the form of range +/- base
function fuzzy(range, base) {
    return (base || 0) + (Math.random() - 0.5) * range * 2;
}


// Don't render particles which are not on canvas
function checkBounds(x, y, width, height) {
    return x < 1 || x >= width - 1 || y < 1 || y >= height - 1;
}

// Emit some particles around current center point
function emit(context) {
    if (context.x == null || context.y == null) {
        return
    }
    // how many new particles emit from the center per drawing
    for (let i = 0; i < 20; i++) {
        context.particles_i = (context.particles_i + NFIELDS) % PARTICLES_LENGTH;
        context.particles[context.particles_i] = context.x;
        context.particles[context.particles_i + 1] = context.y;
        const alpha = fuzzy(Math.PI),
            radius = Math.random() * 200;
        context.particles[context.particles_i + 2] = Math.cos(alpha) * radius;
        context.particles[context.particles_i + 3] = Math.sin(alpha) * radius;
        context.particles[context.particles_i + 4] = Math.random();
    }
}
