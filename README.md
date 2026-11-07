# Student Activity Markov Analysis System

A Java-based desktop application with a Swing Graphical User Interface (GUI) designed to model and predict student daily routines using Markov chains. 

Developed as an experiential learning mini-project for **Linear Algebra & Vector Calculus (MAT2303)** at Presidency University.

---

## 🚀 Key Features
* **Interactive Transition Matrix:** Custom 4x4 matrix input for tracking probabilities between 4 states: *Class, Study, Assignment, and Break*.
* **State Evolution Engine:** Performs repeated vector-matrix multiplication to project activity distributions across 20 simulation steps.
* **Steady-State Approximation:** Iteratively calculates long-term probability distributions and verifies the stationary condition ($\pi P \approx \pi$).
* **Java Swing GUI:** Features real-time probability validation, error handling, reset functionality, and a dynamic convergence graph.

---

## 📊 Mathematical Background
- **States ($S$):** $S_1$ (Class), $S_2$ (Study), $S_3$ (Assignment), $S_4$ (Break).
- **Transition Rule:** $P(t+1) = P(t) \times P$, where each row of transition matrix $P$ sums to $1.0$.
- **Steady-State Condition:** $\pi P = \pi$ evaluated via iterative convergence with strict numerical tolerance checking.

---

## 🛠️ Tech Stack
* **Language:** Java
* **UI Framework:** Java Swing
* **Concepts:** Linear Algebra, Markov Chains, Probability Vectors, Iterative Numerical Convergence
