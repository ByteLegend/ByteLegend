/**
 * Credit: https://github.com/tswaters/fireworks
 * Patched by: https://github.com/blindpirate/fireworks
 */
(function (global, factory) {
    typeof exports === 'object' && typeof module !== 'undefined' ? module.exports = factory() :
        typeof define === 'function' && define.amd ? define('Fireworks', factory) :
            (global = typeof globalThis !== 'undefined' ? globalThis : global || self, global.Fireworks = factory());
}(this, (function () { 'use strict';

    /*! *****************************************************************************
    Copyright (c) Microsoft Corporation.

    Permission to use, copy, modify, and/or distribute this software for any
    purpose with or without fee is hereby granted.

    THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
    REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY
    AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
    INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
    LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR
    OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
    PERFORMANCE OF THIS SOFTWARE.
    ***************************************************************************** */

    function __values(o) {
        var s = typeof Symbol === "function" && Symbol.iterator, m = s && o[s], i = 0;
        if (m) return m.call(o);
        if (o && typeof o.length === "number") return {
            next: function () {
                if (o && i >= o.length) o = void 0;
                return { value: o && o[i++], done: !o };
            }
        };
        throw new TypeError(s ? "Object is not iterable." : "Symbol.iterator is not defined.");
    }

    function random(min, max) {
        return Math.random() * (max - min) + min;
    }
    var TAU = Math.PI * 2;

    var Particle = (function () {
        function Particle(_a) {
            var _b = _a.isRocket, isRocket = _b === void 0 ? false : _b, _c = _a.hue, hue = _c === void 0 ? random(1, 360) : _c, _d = _a.brightness, brightness = _d === void 0 ? random(50, 60) : _d, position = _a.position;
            this.isRocket = isRocket;
            this.position = position;
            this.positions = [this.position, this.position, this.position];
            if (this.isRocket) {
                this.velocity = {
                    x: random(-3, 3),
                    y: random(-7, -3)
                };
                this.shrink = 0.999;
                this.resistance = 1;
            }
            else {
                var angle = random(0, TAU);
                var speed = Math.cos(random(0, TAU)) * 15;
                this.velocity = {
                    x: Math.cos(angle) * speed,
                    y: Math.sin(angle) * speed
                };
                this.shrink = random(0, 0.05) + 0.93;
                this.resistance = 0.92;
            }
            this.gravity = 0.01;
            this.size = 3;
            this.alpha = 1;
            this.fade = 0;
            this.hue = hue;
            this.brightness = brightness;
        }
        Particle.prototype.clone = function () {
            return new Particle({
                position: {
                    x: this.position.x,
                    y: this.position.y
                },
                hue: this.hue,
                brightness: this.brightness
            });
        };
        Particle.prototype.shouldRemove = function (cw, ch) {
            if (this.alpha <= 0.1 || this.size <= 1) {
                return true;
            }
            if (this.position.x > cw || this.position.x < 0) {
                return true;
            }
            if (this.position.y > ch || this.position.y < 0) {
                return true;
            }
            return false;
        };
        Particle.prototype.shouldExplode = function (maxHeight, minHeight, chance) {
            if (!this.isRocket) {
                return false;
            }
            if (this.position.y <= maxHeight) {
                return true;
            }
            if (this.position.y >= minHeight) {
                return false;
            }
            return random(0, 1) <= chance;
        };
        Particle.prototype.update = function () {
            this.positions.pop();
            this.positions.unshift({ x: this.position.x, y: this.position.y });
            this.velocity.x *= this.resistance;
            this.velocity.y *= this.resistance;
            this.velocity.y += this.gravity;
            this.position.x += this.velocity.x;
            this.position.y += this.velocity.y;
            this.size *= this.shrink;
            this.alpha -= this.fade;
        };
        Particle.prototype.draw = function (ctx) {
            var lastPosition = this.positions[this.positions.length - 1];
            ctx.beginPath();
            ctx.moveTo(lastPosition.x, lastPosition.y);
            ctx.lineTo(this.position.x, this.position.y);
            ctx.lineWidth = this.size;
            ctx.lineCap = 'round';
            ctx.strokeStyle = "hsla(" + this.hue + ", 100%, " + this.brightness + "%, " + this.alpha + ")";
            ctx.stroke();
        };
        return Particle;
    }());

    var Things = (function () {
        function Things(_a) {
            var maxRockets = _a.maxRockets, numParticles = _a.numParticles, cw = _a.cw, ch = _a.ch;
            this._set = new Set();
            this.rockets = 0;
            this.maxRockets = maxRockets;
            this.numParticles = numParticles;
            this.cw = cw;
            this.ch = ch;
        }
        Things.prototype.size = function () {
            return this._set.size;
        };
        Things.prototype.entries = function () {
            return this._set;
        };
        Things.prototype.clear = function () {
            this._set.clear();
        };
        Things.prototype["delete"] = function (thing) {
            this._set["delete"](thing);
            if (thing.isRocket)
                this.rockets--;
        };
        Things.prototype.add = function (thing) {
            this._set.add(thing);
        };
        Things.prototype.explode = function (particle) {
            for (var i = 0; i < this.numParticles; i += 1) {
                this.add(particle.clone());
            }
            this["delete"](particle);
        };
        Things.prototype.spawnRocket = function () {
            this.rockets++;
            this.add(new Particle({
                isRocket: true,
                position: {
                    x: this.cw / 2,
                    y: this.ch
                }
            }));
        };
        Things.prototype.spawnRockets = function () {
            if (this.rockets < this.maxRockets) {
                this.spawnRocket();
            }
        };
        return Things;
    }());

    var Fireworks = (function () {
        function Fireworks(container, _a) {
            var _b = _a === void 0 ? {} : _a, _c = _b.rocketSpawnInterval, rocketSpawnInterval = _c === void 0 ? 150 : _c, _d = _b.maxRockets, maxRockets = _d === void 0 ? 3 : _d, _e = _b.numParticles, numParticles = _e === void 0 ? 100 : _e, _f = _b.explosionMinHeight, explosionMinHeight = _f === void 0 ? 0.2 : _f, _g = _b.explosionMaxHeight, explosionMaxHeight = _g === void 0 ? 0.9 : _g, _h = _b.explosionChance, explosionChance = _h === void 0 ? 0.08 : _h, _j = _b.width, width = _j === void 0 ? container.clientWidth : _j, _k = _b.height, height = _k === void 0 ? container.clientHeight : _k;
            this.finishCallbacks = [];
            this.container = container;
            this.rocketSpawnInterval = rocketSpawnInterval;
            this.maxRockets = maxRockets;
            this.cw = width;
            this.ch = height;
            this.maxH = this.ch * (1 - explosionMaxHeight);
            this.minH = this.ch * (1 - explosionMinHeight);
            this.chance = explosionChance;
            this.pixelRatio = window.devicePixelRatio || 1;
            this.canvas = document.createElement('canvas');
            this.ctx = this.canvas.getContext('2d');
            container.appendChild(this.canvas);
            this.things = new Things({
                maxRockets: this.maxRockets,
                numParticles: numParticles,
                cw: this.cw,
                ch: this.ch
            });
            this.updateDimensions();
        }
        Fireworks.prototype.destroy = function () {
            this.canvas.parentElement.removeChild(this.canvas);
            window.clearInterval(this.interval);
            window.cancelAnimationFrame(this.rafInterval);
        };
        Fireworks.prototype.start = function () {
            var _this = this;
            if (this.maxRockets > 0) {
                this.interval = window.setInterval(function () { return _this.things.spawnRockets(); }, this.rocketSpawnInterval);
                this.rafInterval = window.requestAnimationFrame(function () { return _this.update(); });
            }
            return function () { return _this.stop(); };
        };
        Fireworks.prototype.updateDimensions = function () {
            this.canvas.width = this.cw * this.pixelRatio;
            this.canvas.height = this.ch * this.pixelRatio;
            this.canvas.style.width = this.cw + 'px';
            this.canvas.style.height = this.ch + 'px';
            this.ctx.scale(this.pixelRatio, this.pixelRatio);
            this.things.cw = this.cw;
            this.things.ch = this.ch;
        };
        Fireworks.prototype.setSize = function (width, height) {
            this.cw = width;
            this.ch = height;
            this.updateDimensions();
        };
        Fireworks.prototype.resetSize = function () {
            this.cw = this.container.clientWidth;
            this.ch = this.container.clientHeight;
            this.updateDimensions();
        };
        Fireworks.prototype.stop = function () {
            window.clearInterval(this.interval);
            this.interval = null;
        };
        Fireworks.prototype.kill = function () {
            this.things.clear();
            this.stop();
            window.cancelAnimationFrame(this.rafInterval);
            this._finish();
        };
        Fireworks.prototype.fire = function () {
            var _this = this;
            this.things.spawnRocket();
            if (!this.rafInterval) {
                this.rafInterval = window.requestAnimationFrame(function () { return _this.update(); });
            }
        };
        Fireworks.prototype.onFinish = function (cb) {
            this.finishCallbacks.push(cb);
        };
        Fireworks.prototype._clear = function (force) {
            if (force === void 0) { force = false; }
            this.ctx.globalCompositeOperation = 'destination-out';
            this.ctx.fillStyle = "rgba(0, 0, 0 " + (force ? '' : ', 0.5') + ")";
            this.ctx.fillRect(0, 0, this.cw, this.ch);
            this.ctx.globalCompositeOperation = 'lighter';
        };
        Fireworks.prototype._finish = function () {
            this._clear(true);
            this.rafInterval = null;
            this.finishCallbacks.forEach(function (cb) { return cb(); });
        };
        Fireworks.prototype.update = function () {
            var e_1, _a;
            var _this = this;
            this._clear();
            try {
                for (var _b = __values(this.things.entries()), _c = _b.next(); !_c.done; _c = _b.next()) {
                    var particle = _c.value;
                    particle.draw(this.ctx);
                    particle.update();
                    if (particle.shouldRemove(this.cw, this.ch)) {
                        this.things["delete"](particle);
                    }
                    else if (particle.shouldExplode(this.maxH, this.minH, this.chance)) {
                        this.things.explode(particle);
                    }
                }
            }
            catch (e_1_1) { e_1 = { error: e_1_1 }; }
            finally {
                try {
                    if (_c && !_c.done && (_a = _b["return"])) _a.call(_b);
                }
                finally { if (e_1) throw e_1.error; }
            }
            if (this.interval || this.things.size() > 0) {
                this.rafInterval = window.requestAnimationFrame(function () { return _this.update(); });
            }
            else {
                this._finish();
            }
        };
        return Fireworks;
    }());

    return Fireworks;

})));
