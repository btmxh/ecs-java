package com.dah.ecsj.test

import com.dah.ecsj.*
import com.dah.ecsj.systems.FamilySystem

class Position(var x : Float, var y : Float) : Component() {
    override fun toString(): String {
        return "Position(x=$x, y=$y)"
    }
}
class Gravity(var g : Float) : Component()
class Movement(var vx : Float, var vy : Float) : Component() {
    override fun toString(): String {
        return "Movement(vx=$vx, vy=$vy)"
    }
}

class GravitySystem(engine: Engine<Double>) : FamilySystem<Double>(Family.bitBuilder()
    .all(compType(engine, Position::class.java))
    .all(compType(engine, Gravity::class.java))
    .all(compType(engine, Movement::class.java)).get()
) {

    override fun run(entity: Entity, runData: Double) {
        super.run(entity, runData)

        val g = entity[Gravity::class.java]!!
        val m = entity[Movement::class.java]!!
        m.vy += runData.toFloat() * g.g
    }
}

fun clampf(min : Float, f : Float, max : Float) : Float {
    return if(f <= min) min else (if(f >= max) max else f)
}

class MovementSystem(engine: Engine<Double>, private val maxVx : Float = 100.0f, private val maxVy : Float = 100.0f)
    : FamilySystem<Double>(Family.bitBuilder()
        .all(compType(engine, Position::class.java))
        .all(compType(engine, Movement::class.java))
        .get()
    ) {
    override fun run(entity: Entity, delta: Double) {
        super.run(entity, delta)

        val p = entity[Position::class.java]!!
        val m = entity[Movement::class.java]!!

        m.vx = clampf(-maxVx, m.vx, maxVx)
        m.vy = clampf(-maxVy, m.vy, maxVy)

        p.x += (m.vx * delta).toFloat()
        p.y += (m.vy * delta).toFloat()
    }
}

fun main() {
    val engine = Engine<Double>()

    val player = Entity()

    engine.entityManager += player

    player += Position(3.0f, 5.0f)
    player += Gravity(10.0f)
    player += Movement(2.0f, -0.0f)

    val gravitySystem = GravitySystem(engine)
    val movementSystem = MovementSystem(engine)

    engine.systemManager += gravitySystem
    engine.systemManager += movementSystem

    val delta = 1.0
    val sleep = 1.0

    while(true) {
        engine.run(delta)

        println(player[Position::class.java])
        println(player[Movement::class.java])
        Thread.sleep((1000 * sleep).toLong())
    }
}