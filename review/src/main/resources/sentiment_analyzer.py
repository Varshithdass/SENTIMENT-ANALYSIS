import nltk
from nltk.sentiment import SentimentIntensityAnalyzer
import sys
import json

# Download required NLTK data
nltk.download('vader_lexicon')

def analyze_sentiment(text):
    sia = SentimentIntensityAnalyzer()
    sentiment_scores = sia.polarity_scores(text)
    
    # Determine if the sentiment is positive or negative
    if sentiment_scores['compound'] >= 0.05:
        return "positive"
    elif sentiment_scores['compound'] <= -0.05:
        return "negative"
    else:
        return "neutral"

if __name__ == "__main__":
    # Read input from command line
    review_text = sys.argv[1]
    result = analyze_sentiment(review_text)
    print(json.dumps({"sentiment": result})) 