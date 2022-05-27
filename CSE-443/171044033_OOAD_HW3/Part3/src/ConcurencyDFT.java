/**
 * Concurency DFT Class
 */
public class ConcurencyDFT
{
    private volatile int THREAD_COUNT = 4;
    private volatile int FINISH_BARRIER = 0;

    private Thread[] threads;

    private final Object monitor = new Object();

    private ComplexNumber[][] matrixA;
    private ComplexNumber[][] matrixB;

    private ComplexNumber[][] sumMatrix;

    private ComplexNumber[][] transformedMatrix;

    private volatile boolean interrupt;
    private boolean singleThread;
    private Object response;

    /**
     * Consturctor
     * @param MATRIX_SIZE matrix size
     * @param response response mutex objec for synchronization between this class and GUI thread
     * @param singleThread single or multi thread information
     * @throws InterruptedException interrupt excpetion
     */
    public ConcurencyDFT(int MATRIX_SIZE, Object response, boolean singleThread) throws InterruptedException {

        this.interrupt = false;
        this.singleThread = singleThread;
        this.response = response;
        this.matrixA = new ComplexNumber[MATRIX_SIZE][MATRIX_SIZE];
        this.matrixB = new ComplexNumber[MATRIX_SIZE][MATRIX_SIZE];
        this.sumMatrix = new ComplexNumber[MATRIX_SIZE][MATRIX_SIZE];
        this.transformedMatrix = new ComplexNumber[MATRIX_SIZE][MATRIX_SIZE];

        for (int i = 0; i < MATRIX_SIZE; ++i) {
            for (int j = 0; j < MATRIX_SIZE; ++j) {
                this.matrixA[i][j] = new ComplexNumber();
                this.matrixB[i][j] = new ComplexNumber();
            }
        }

        if (!singleThread) {
            this.threads = new Thread[THREAD_COUNT];
            int thread_idx = 0;
            for (int i = 0; i < THREAD_COUNT / 2; i++) {

                int start_row = i * MATRIX_SIZE / 2;
                int end_row = start_row + MATRIX_SIZE / 2;

                for (int j = 0; j < THREAD_COUNT / 2; j++) {

                    int start_column = j * MATRIX_SIZE / 2;
                    int end_column = start_column + MATRIX_SIZE / 2;

                    int id = thread_idx;
                    threads[thread_idx++] = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try { sumMatrix(start_row, start_column, end_row - 1, end_column - 1, id);}
                            catch (InterruptedException e) { e.printStackTrace(); }
                        }
                    });
                }
            }
        } else {
            THREAD_COUNT = 1;
            this.threads = new Thread[THREAD_COUNT];
            this.threads[0] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try { sumMatrix(0, 0, MATRIX_SIZE - 1, MATRIX_SIZE - 1, 0); }
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
            });
        }
    }

    /**
     * start operation
     */
    public void startThreads() {
        for (int i = 0; i < threads.length; ++i) {
            threads[i].start();
        }
    }

    /**
     * cancel operation
     * @throws InterruptedException interrupt exception
     */
    public void stopThreads() throws InterruptedException {
        this.interrupt = true;
        for(int i = 0; i < threads.length; ++i) {
            threads[i].join();
        }
    }

    /**
     * finished or not
     * @return finished or not
     */
    public boolean isFinished() {

        synchronized (monitor) {
            if (singleThread && FINISH_BARRIER == 1) {
                return true;
            }  else if (FINISH_BARRIER == 4) {
                return true;
            }
        }
        return false;
    }

    /**
     * sum matrix and dft it
     * @param s_i start row
     * @param s_j start column
     * @param f_i finish row
     * @param f_j finish column
     * @param id thread id
     * @throws InterruptedException interrupt exception
     */
    private void sumMatrix(int s_i, int s_j, int f_i, int f_j, int id) throws InterruptedException {

        for (int i = s_i; i <= f_i; ++i) {
            for (int j = s_j; j <= f_j; ++j) {
                if (interrupt) { return; }
                sumMatrix[i][j] = matrixA[i][j].sumComp(matrixB[i][j]);
            }
        }

        /**
         * Synchronization Barrier Part
         */
        synchronized (monitor) {

            --THREAD_COUNT;

            if (THREAD_COUNT != 0) {
                monitor.wait();
            } else {
                matrixA = null;
                matrixB = null;
                monitor.notifyAll();
            }
        }
        if (interrupt) { return; }

        int idx = 0;
        int count = 0;
        for (int i = s_i; i <= f_i; ++i) {
            for (int j = s_j; j <= f_j; ++j) {
                if (interrupt) { return; }
                double sumreal = 0;
                double sumimag = 0;
                int which = 0;
                for (int k = s_i; k <= f_i; ++k) {
                    if (interrupt) { return; }
                    double angle = 2 * Math.PI * (which++) * (idx++) / (f_i - s_i + 1);
                    sumreal += sumMatrix[i][j].get_real() * Math.cos(angle) + sumMatrix[i][j].get_img() * Math.sin(angle);
                    sumimag += sumMatrix[i][j].get_real() * Math.sin(angle) + sumMatrix[i][j].get_img() * Math.cos(angle);
                }
                transformedMatrix[i][j] = new ComplexNumber(sumreal, sumimag);
            }
            sumMatrix[i][f_j] = null;
        }

        /**
         * My additional part for synchronized gui and this class
         */
        synchronized (response) {

            ++FINISH_BARRIER;

            if (singleThread) {
                response.notify();
            }  else if (FINISH_BARRIER == 4)  {
                response.notify();
            }
        }
    }
}