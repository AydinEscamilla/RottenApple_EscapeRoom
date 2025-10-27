/**
 * @Author: Rotten Apple
 * CSCE247
 */
package com.model;
/* 
 * Represents the different difficulties for Puzzles.
 * These difficulties are used for scoring logic, UI display, or giving a different experience.
*/
public enum Difficulty {
    /** A introducion difficulty experience, all puzzles are default this way for onboarding . */
    EASY,
    //** A standard difficulty experience, requires more reasoning. */
    MEDIUM,
    //** A advanced difficulty experience, has multiple steps or trickier logic. */
    HARD
}
