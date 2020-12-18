package by.it.soldatenko.jd02_02;


class Buyer extends Thread implements IBuyer, IUseBasket {
    private boolean isRunnable;

    public void setRunnable(boolean iWait) {
        this.isRunnable = iWait;
    }

    public Buyer(int number) {
        super("Buyer №" + number + " ");
        Dispetcher.addBuyer();

    }

    @Override
    public void run() {
        enterToMarket();
        takeBasket();
        chooseGoods();
        goToQueue();
        goOut();
        Dispetcher.completeBuyer();
    }

    static boolean pensioneer() {

        int pens = Helper.getRandom(1, 4);
        boolean pensioneer = false;
        if (pens == 1) {
            pensioneer = true;
        }
        return pensioneer;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public void enterToMarket() {
        Market.quantityIn++;
        System.out.println(this + "entered to Market");
    }

    @Override
    public void chooseGoods() {
        double speed = 1;
        if (pensioneer()) {
            speed = 1.5;
        }
        System.out.println(this + " started to choose goods");
        int goodsAmount = Helper.getRandom(1, 4);
        for (int i = 1; i <= goodsAmount; i++) {
            Helper.sleep((int) speed * Helper.getRandom(500, 2000));

            putGoodsToBasket();
        }
        System.out.println(this + " finishded to choose goods");
    }

    @Override
    public void goToQueue() {
        QueueBuyers.add(this);
        System.out.println(this+ " added to Queue");
        this.setRunnable(false);
        synchronized (this) {
            while (!this.isRunnable)
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        System.out.println(this+ " left the Queue");
    }

    @Override
    public void takeBasket() {
        System.out.println(this + " taked the Basket");

    }

    @Override
    public void putGoodsToBasket() {
        System.out.println(this + " puted " + Good.getGood() + " to the Basket");

    }

    @Override
    public void goOut() {
        System.out.println(this + " leaved the Market");
        Market.quantityOut++;
    }
}
