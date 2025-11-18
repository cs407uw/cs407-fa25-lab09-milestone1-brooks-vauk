package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        // X axis
        // New velocity using avg of old and new acceleration
        val newVelocityX = velocityX + 0.5f * (accX + xAcc) * dT

        // Distance moved in x during this time
        val deltaX = velocityX * dT +
                (1f / 6f) * dT * dT * (3f * accX + xAcc)

        // Y axis
        val newVelocityY = velocityY + 0.5f * (accY + yAcc) * dT

        val deltaY = velocityY * dT +
                (1f / 6f) * dT * dT * (3f * accY + yAcc)

        // Apply the updates
        velocityX = newVelocityX
        velocityY = newVelocityY

        posX += deltaX
        posY += deltaY

        // Store latest accelerations for next step
        accX = xAcc
        accY = yAcc

        // Keep the ball inside the field
        checkBoundaries()
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)
        val minX = 0f
        val minY = 0f
        val maxX = backgroundWidth - ballSize
        val maxY = backgroundHeight - ballSize

        // Left wall
        if (posX < minX) {
            posX = minX
            velocityX = 0f
            accX = 0f
        }

        // Right wall
        if (posX > maxX) {
            posX = maxX
            velocityX = 0f
            accX = 0f
        }

        // Top wall
        if (posY < minY) {
            posY = minY
            velocityY = 0f
            accY = 0f
        }

        // Bottom wall
        if (posY > maxY) {
            posY = maxY
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = (backgroundWidth - ballSize) / 2f
        posY = (backgroundHeight - ballSize) / 2f

        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f

        isFirstUpdate = true
    }
}