package com.dah.ecsj.test

import com.dah.ecsj.*
import com.dah.ecsj.systems.FamilySystem

class Position(val x : Float, val y : Float) : Component()
class Gravity(val g : Float) : Component()
class Movement(val vx : Float, val vy : Float) : Component()

class GravitySystem(engine: Engine<Double>) : FamilySystem<Double>(Family.BitFamilyBuilder()
    .all(compType(engine, Position::class.java))
    .all(compType(engine, Gravity::class.java))
    .all(compType(engine, Movement::class.java)).get()) {

    override fun run(entity: Entity, runData: Double) {
        super.run(entity, runData)
        val g = entity[compType(engine, Gravity::class.java)]!!
        val m = entity[compType(engine, Movement::class.java)]!!
        entity[compType(engine, Position::class.java)]!!.apply {
            m.vy += runData.toFloat() * g.g;
        }
    }
}

fun main() {
    val clazz = String::class.java
    val engine = Engine<Double>()

    val player = Entity()
    player += Position(3.0f, 5.0f)
    player += Gravity(10.0f)
    player += Movement(2.0f, -1.0f)

    engine.entityManager += player

    val gravitySystem =
}